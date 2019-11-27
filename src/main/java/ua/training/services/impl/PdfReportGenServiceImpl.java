package ua.training.services.impl;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

	public void substituteFields(String src, String dest, String[] inputValues) throws DocumentException, IOException {
		PdfReader reader = new PdfReader(src);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		AcroFields form = stamper.getAcroFields();
		LOG.info(String.format("obtained form name - %s", form.toString()));
		for(String fieldValue : inputValues) {
			form.setField(fieldValue, "stub");
			LOG.info(String.format("field value was written - %s", form.getField("name")));
		}
		stamper.setFormFlattening(true);
		stamper.close();
	}
}






