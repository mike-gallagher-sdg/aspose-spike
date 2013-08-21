package sdg.aspose;

import java.io.File;
import java.io.IOException;

import aspose.pdf.Color;
import aspose.pdf.Graph;
import aspose.pdf.HeaderFooter;
import aspose.pdf.Line;
import aspose.pdf.MarginInfo;
import aspose.pdf.PageSize;
import aspose.pdf.Pdf;
import aspose.pdf.Section;
import aspose.pdf.Text;
import aspose.pdf.TextInfo;

public class Main {
    
    public static final float POINTS_PER_INCH = 72;
    public static final float PAGE_TOP_MARGIN = .75f * POINTS_PER_INCH;
    public static final float PAGE_LEFT_MARGIN = .75f * POINTS_PER_INCH;
    public static final float PAGE_RIGHT_MARGIN = .75f * POINTS_PER_INCH;
    public static final float PAGE_BOTTOM_MARGIN = .5f * POINTS_PER_INCH;
    public static final float PAGE_HEIGHT = 11f * POINTS_PER_INCH;
    
    public static final String ACROBAT_READER_EXE = "C:\\Program Files (x86)\\Adobe\\Reader 10.0\\Reader\\AcroRd32.exe";
    
    // #1F69AA => 31, 105, 170
    public static final Color HEADER_COLOR = new Color((short)31, (short)105, (short)170);
    public static final String HEADER_FONT_NAME = "Arial Bold";
    public static final float HEADER_FONT_SIZE = 14f;
    
    
    public static void main(String[] args) throws IOException {
	
	Main main = new Main();
	File generatedPdf = File.createTempFile("spike-", ".pdf");
	main.createPdf(generatedPdf.getCanonicalPath());
	
	String cmd = String.format("\"%s\" %s", ACROBAT_READER_EXE, generatedPdf.getCanonicalFile());
	System.out.println(String.format("Executing [%s]", cmd));
	Runtime.getRuntime().exec(cmd);
    }

    public void createPdf(String filename) {
	
	Pdf pdf = new Pdf();
	Section section = pdf.getSections().add();
	
	setPageProperties(section);
	setPageHeader(section, "Submittal");
	
	pdf.save(filename);
	
	System.out.println(String.format("Created PDF [%s]", filename));
    }

    private void setPageHeader(Section section, String headerText) {
	TextInfo textInfo = new TextInfo();
	textInfo.setColor(HEADER_COLOR);
	textInfo.setFontName(HEADER_FONT_NAME);
	textInfo.setFontSize(HEADER_FONT_SIZE);
	
	Text text = new Text(headerText);
	text.setTextInfo(textInfo);
	section.getParagraphs().add(text);
	
	section.getParagraphs().add(createHorizontalRule(section, HEADER_COLOR));
    }

    private void setPageProperties(Section section) {
	section.getPageInfo().setPageWidth(PageSize.LetterWidth);
	section.getPageInfo().setPageHeight(PageSize.LetterHeight);
	
	MarginInfo marginInfo = new MarginInfo();
	marginInfo.setTop(PAGE_TOP_MARGIN);
	marginInfo.setLeft(PAGE_LEFT_MARGIN);
	marginInfo.setRight(PAGE_RIGHT_MARGIN);
	marginInfo.setBottom(PAGE_BOTTOM_MARGIN);
	section.getPageInfo().setMargin(marginInfo);
    }
    
    protected Graph createHorizontalRule(Section section, Color color) {
	float graphWidth = 500;
	float graphHeight = 1;
	Graph graph = new Graph(section, graphWidth, graphHeight);
	
	float lineStartX = 0;
	float lineStartY = 0;
	float lineEndX = 500;
	float lineEndY = 0;
	
	float[] linePositions = new float[]{lineStartX, lineStartY, lineEndX, lineEndY}; 
	Line line = new Line(graph, linePositions); 
	line.getGraphInfo().setColor(color); 
	
	graph.getShapes().add(line);

	return graph;
    }

}
