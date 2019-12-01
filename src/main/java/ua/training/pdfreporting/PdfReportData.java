package ua.training.pdfreporting;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PdfReportData {

    private final Collection<String> list;

    public PdfReportData(Collection<String> c) {
        list = new ArrayList<>(c);
    }

    public JasperPrint getReport() throws JRException {
        Style headerStyle = createHeaderStyle();
        Style detailTextStyle = createDetailTextStyle();
        Style detailNumberStyle = createDetailNumberStyle();
        DynamicReport dynaReport = getReport(headerStyle, detailTextStyle, detailNumberStyle);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(),
                new JRBeanCollectionDataSource(list));
        return jp;
    }

    private Style createHeaderStyle() {
        return new StyleBuilder(true)
                .setFont(Font.VERDANA_MEDIUM_BOLD)
                .setBorder(Border.THIN())
                .setBorderBottom(Border.PEN_2_POINT())
                .setBorderColor(Color.BLACK)
                .setBackgroundColor(Color.ORANGE)
                .setTextColor(Color.BLACK)
                .setHorizontalAlign(HorizontalAlign.CENTER)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setTransparency(Transparency.OPAQUE)
                .build();
    }

    private Style createDetailTextStyle() {
        return new StyleBuilder(true)
                .setFont(Font.VERDANA_MEDIUM)
                .setBorder(Border.DOTTED())
                .setBorderColor(Color.BLACK)
                .setTextColor(Color.BLACK)
                .setHorizontalAlign(HorizontalAlign.LEFT)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setPaddingLeft(5)
                .build();
    }

    private Style createDetailNumberStyle() {
        return new StyleBuilder(true)
                .setFont(Font.VERDANA_MEDIUM)
                .setBorder(Border.DOTTED())
                .setBorderColor(Color.BLACK)
                .setTextColor(Color.BLACK)
                .setHorizontalAlign(HorizontalAlign.RIGHT)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setPaddingRight(5)
                .setPattern("#,##0.00")
                .build();
    }

    private AbstractColumn createColumn(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle) {
        return ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName())
                .setTitle(title)
                .setWidth(Integer.valueOf(width))
                .setStyle(detailStyle)
                .setHeaderStyle(headerStyle)
                .build();
    }

    private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle) {

        DynamicReportBuilder report = new DynamicReportBuilder();

        AbstractColumn columnEmpNo = createColumn("id", Long.class, "id", 30, headerStyle, detailTextStyle);
        AbstractColumn columnName = createColumn("companyName", String.class, "companyName", 30, headerStyle, detailTextStyle);
        AbstractColumn columnSalary = createColumn("totalAmountOfProperty", Long.class, "totalAmountOfProperty", 30, headerStyle, detailNumStyle);

        report.addColumn(columnEmpNo)
                .addColumn(columnName)
                .addColumn(columnSalary);

        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, null, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, null, true));

        report.setTitle("title");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle("subtitle");
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true);
        return report.build();
    }
}