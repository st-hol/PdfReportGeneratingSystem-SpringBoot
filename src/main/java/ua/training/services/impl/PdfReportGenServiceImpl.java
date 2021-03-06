package ua.training.services.impl;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PdfReportGenServiceImpl {

    public static final String FONT = "/arial.ttf";

    private static final Logger LOG = LoggerFactory.getLogger(PdfReportGenServiceImpl.class);


    /**
     * get acrofield stamps
     * @deprecated : UNORDERED
     *
     * @param pdfBytes
     * @return
     * @throws IOException
     */
    @Deprecated
    public Set<String> getFieldNames(byte[] pdfBytes)
            throws IOException {
        PdfReader reader = new PdfReader(pdfBytes);
        AcroFields fields = reader.getAcroFields();
        return fields.getFields().keySet();//getSignatureNames
    }

    /**
     * get acrofield stamps ORDERED
     *
     * @param pdfBytes
     * @return
     * @throws IOException
     */
    public List<String> getFieldNamesOrdered(byte[] pdfBytes)
            throws IOException {
        PdfReader reader = new PdfReader(pdfBytes);
        AcroFields fields = reader.getAcroFields();

        Map<String, AcroFields.Item> fieldItemMap = fields.getFields();

        Comparator<Map.Entry<String, AcroFields.Item>> byItem =
                Comparator.comparing(
                        (Map.Entry<String, AcroFields.Item> o) -> o.getValue().getTabOrder(0));

        return fieldItemMap.entrySet().stream()
                .sorted(byItem)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public byte[] substituteFields(byte[] pdfBytes, Map<String, String> fieldNameAndValueMap)
            throws DocumentException, IOException {
        PdfReader reader = new PdfReader(pdfBytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);
        AcroFields form = stamper.getAcroFields();

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        form.addSubstitutionFont(bf);

        LOG.info("obtained form");
        for (Map.Entry<String, String> field : fieldNameAndValueMap.entrySet()) {
            form.setField(field.getKey(), field.getValue());
            LOG.info(MessageFormat.format("field value was written: {0} to field {1}",
                    field.getKey(), field.getValue()));
        }
        stamper.setFormFlattening(true);
        stamper.close();
        return baos.toByteArray();
    }

    /**
     * loads pdf from byte[] and sets fields by index - mistake...incoming Set dont support order.
     * @deprecated : use method with HashMap
     *
     * @param pdfBytes
     * @param fieldNames
     * @param inputValues
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    @Deprecated
    public byte[] substituteFields(byte[] pdfBytes, String[] fieldNames, String[] inputValues)
            throws DocumentException, IOException {
        PdfReader reader = new PdfReader(pdfBytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, baos);
        AcroFields form = stamper.getAcroFields();

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        form.addSubstitutionFont(bf);

        LOG.info("obtained form");
        for (int i = 0; i < inputValues.length; i++) {
            form.setField(fieldNames[i], inputValues[i]);
            LOG.info(MessageFormat.format("field value was written: {0} to field {1}",
                    inputValues[i], fieldNames[i]));
        }
        stamper.setFormFlattening(true);
        stamper.close();
        return baos.toByteArray();
    }

    /**
     * loads pdf from resources
     *
     * @param src
     * @param dest
     * @param inputValues
     * @param fieldNames
     * @throws DocumentException
     * @throws IOException
     */
    public void substituteFields(String src, String dest,
                                 String[] inputValues, String[] fieldNames)
            throws DocumentException, IOException {

        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        LOG.info(String.format("obtained form name - %s", form.toString()));
        for (int i = 0; i < inputValues.length; i++) {
            form.setField(fieldNames[i], inputValues[i]);
            LOG.info(String.format("field value was written - %s", form.getField(inputValues[i])));
        }
        stamper.setFormFlattening(true);
        stamper.close();
    }

}


//    private BaseFont getFont(final int style, final String resourceName)
//            throws IOException, DocumentException {
//        InputStream is = getClass().getResourceAsStream(resourceName);
//        byte[] fdata = IOUtils.toByteArray(is);
//        IOUtils.closeQuietly(is);
//        return BaseFont.createFont(resourceName, BaseFont.CP1252, BaseFont.EMBEDDED,true, fdata,null);
//    }


//	public byte[] getReportPdf(final JasperPrint jp) throws JRException {
//		return JasperExportManager.exportReportToPdf(jp);
//	}
//
//	public byte[] getReportXlsx(final JasperPrint jp) throws JRException, IOException {
//		JRXlsxExporter xlsxExporter = new JRXlsxExporter();
//		final byte[] rawBytes;
//
//		try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){
//			xlsxExporter.setExporterInput(new SimpleExporterInput(jp));
//			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
//			xlsxExporter.exportReport();
//
//			rawBytes = xlsReport.toByteArray();
//		}
//
//		return rawBytes;
//	}
