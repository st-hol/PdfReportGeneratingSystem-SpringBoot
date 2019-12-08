package ua.training.services.impl;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfReportGenServiceImpl {

	private static final Logger LOG = LoggerFactory.getLogger(PdfReportGenServiceImpl.class);


	public byte[] getReportPdf(final JasperPrint jp) throws JRException {
		return JasperExportManager.exportReportToPdf(jp);
	}
	
	public byte[] getReportXlsx(final JasperPrint jp) throws JRException, IOException {
		JRXlsxExporter xlsxExporter = new JRXlsxExporter();
		final byte[] rawBytes;
		
		try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){
			xlsxExporter.setExporterInput(new SimpleExporterInput(jp));
			xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
			xlsxExporter.exportReport();

			rawBytes = xlsReport.toByteArray();
		}
		
		return rawBytes;
	}

    public byte[] getReportTemplateFromDB() {
        //todo check if fields saved to db
        return new byte[]{1};
    }

    public byte[] saveFilledReportToDB() {
        //todo
        return new byte[]{1};
    }

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

//	private byte[] getByteArrayFromFile(final Document handledDocument) throws IOException {
//		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		final InputStream in = new FileInputStream(handledDocument);
//		final byte[] buffer = new byte[500];
//		int read = -1;
//		while ((read = in.read(buffer)) > 0) {
//			baos.write(buffer, 0, read);
//		}
//		in.close();
//		return baos.toByteArray();
//	}
}


