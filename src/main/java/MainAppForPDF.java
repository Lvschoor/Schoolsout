import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import model.Grade;
import services.GradesForPDFService;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Scanner;


public class MainAppForPDF {



    public static void main(String[] args) {

        GradesForPDFService gradesForPDFService = new GradesForPDFService();
        String dest = "C:\\Users\\lvsch\\OneDrive\\Documenten\\INTEC JAVA IoT\\inputoutput\\pdfs\\FirstPdf.pdf";
        java.util.List<Grade> grades;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give the user to export his grades to pdf: ");
        String userName = scanner.nextLine();

        grades = gradesForPDFService.gradesToPDF(userName);
        String title = grades.get(0).getPerson().getFirstname() + " " + grades.get(0).getPerson().getFamilyname();

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument);
            String imageFile = "C:\\Users\\lvsch\\IdeaProjects\\schoolsout\\src\\main\\resources\\images\\intec.jpg";
            try {
                ImageData data = ImageDataFactory.create(imageFile);
                Image img = new Image(data);
                img.scaleAbsolute(109f, 21f);
                // img.setFixedPosition(100, 250);
                document.add(img);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Paragraph para1 = new Paragraph ("--- "+title+" ---");
            Paragraph para2 = new Paragraph("____________________________________");
            document.add(para1);
            document.add(para2);

            List list = new List();
            String examName;
            for (Grade grade : grades) {
                examName=grade.getExam().getName();

                if (grade.getExam().getExamGroup()!=null){
                    examName= grade.getExam().getExamGroup().getName()+" /\n\t\t\t "+examName;
                }
                list.add(examName + ": " + grade.getGradeValue() + "/" + grade.getExam().getTotal());
            }
            document.add(list);



            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
