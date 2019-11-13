package ua.training.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class PdfReportGenServiceImpl {
	
	public PdfReportGenServiceImpl() {
		
	}
	
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
}
