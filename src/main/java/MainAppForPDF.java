import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import model.Exam;
import model.Grade;
import services.GradesForPDFService;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainAppForPDF {


    public static void main(String[] args) {

        GradesForPDFService gradesForPDFService = new GradesForPDFService();
        Image img = null;

        java.util.List<Grade> grades;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give the user to export his grades to pdf: ");
        String userName = scanner.nextLine();
        grades = gradesForPDFService.gradesToPDF(userName);
        if (!grades.isEmpty()) {
            String titleOfPdf = grades.get(0).getPerson().getFirstname()
                    + " " + grades.get(0).getPerson().getFamilyname();


            String destination = System.getProperty("user.home") + "/downloads/" + titleOfPdf + ".pdf";

            try {
                PdfWriter writer = new PdfWriter(destination);
                PdfDocument pdfDocument = new PdfDocument(writer);
                pdfDocument.addNewPage();
                Document document = new Document(pdfDocument);
                String imageFile = "images/intec.jpg";
                try {
                    ImageData data = ImageDataFactory.create(imageFile);
                    img = new Image(data);
                    img.scaleAbsolute(109f, 21f);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                Table table = new Table(new float[]{2, 4});
                table.setWidthPercent(100);
                table.setMarginBottom(15);

                table.addCell(new Cell().add(img));
                table.addCell(new Cell().add("Rouppeplein 16, 1000 Brussels, Belgium"));

                Table table2 = new Table(new float[]{2, 4});
                table2.useAllAvailableWidth();
                table2.setMarginBottom(15);
                table2.setBackgroundColor(Color.LIGHT_GRAY);


                table2.addCell(new Cell().add(titleOfPdf));
                table2.addCell((new Cell().add("JAVA IoT 21 Apr")));


                Table table3 = new Table(new float[]{3, 1});
                table3.useAllAvailableWidth();


                String examName;

                for (Grade grade : grades) {
                    String indent = "-->";
                    examName = grade.getExam().getName();
                    Exam exam = grade.getExam();

                    while (exam.getExamGroup() != null) {
                        examName = exam.getExamGroup().getName() + " /\n" + indent + examName;
                        exam = exam.getExamGroup();
                    }
                    table3.addCell(new Cell().add(examName));
                    Cell gradeCell = new Cell();
                    gradeCell.setVerticalAlignment(VerticalAlignment.BOTTOM);
                    gradeCell.setBackgroundColor(Color.LIGHT_GRAY);
                    gradeCell.add(grade.getGradeValue() + "/" + grade.getExam().getTotal());
                    table3.addCell(gradeCell);
                }
                document.add(table);
                document.add(table2);
                document.add(table3);

                document.close();
                System.out.println("PDF created in your downloads folder.");
                if (Desktop.isDesktopSupported()) {

                    File myFile = new File(destination);
                    try {
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("No grades found for this user or user does not exists.");
    }
}
