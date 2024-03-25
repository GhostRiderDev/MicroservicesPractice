package com.api.production.model;

import com.api.production.wrapper.HardwareWrapper;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfEntity {
    private List<HardwareWrapper> listHardware;

    public PdfEntity(List<HardwareWrapper> listHardware) {
        this.listHardware = listHardware;
    }

    private void writeTableHeader(PdfPTable pdfPTable) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);
        cell.setPhrase(new Phrase("Id", font));
        pdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        pdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Unit cost", font));
        pdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Type", font));
        pdfPTable.addCell(cell);
    }

    private void writeTableData(PdfPTable pdfPTable) {
        for(HardwareWrapper hardware: listHardware) {
            pdfPTable.addCell(String.valueOf(hardware.getId()));
            pdfPTable.addCell(String.valueOf(hardware.getName()));
            pdfPTable.addCell(String.valueOf(hardware.getUnit_cost()));
            pdfPTable.addCell(String.valueOf(hardware.getCategory()));
        }
    }

    public void export(HttpServletResponse httpServletResponse) throws IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, httpServletResponse.getOutputStream());
        doc.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        Paragraph p = new Paragraph("List of Hardware", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p.setSpacingBefore(20);
        p.setSpacingAfter(20);
        doc.add(p);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.3f, 3.5f, 3.5f, 3.5f});
        table.setSpacingAfter(10);
        writeTableHeader(table);
        writeTableData(table);
        doc.add(table);
        doc.close();
    }
}
