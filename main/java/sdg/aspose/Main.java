package sdg.aspose;

import aspose.pdf.Color;
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
    
    
    public static void main(String[] args) {
	
	Main main = new Main();
	main.createPdf("c:/temp/spike.pdf");
    }

    public void createPdf(String filename) {
	
	Pdf pdf = new Pdf();
	Section section = pdf.getSections().add();
	
	section.getPageInfo().setPageWidth(PageSize.LetterWidth);
	section.getPageInfo().setPageHeight(PageSize.LetterHeight);
	
	MarginInfo marginInfo = new MarginInfo();
	marginInfo.setTop(PAGE_TOP_MARGIN);
	marginInfo.setLeft(PAGE_LEFT_MARGIN);
	marginInfo.setRight(PAGE_RIGHT_MARGIN);
	marginInfo.setBottom(PAGE_BOTTOM_MARGIN);
	section.getPageInfo().setMargin(marginInfo);
	
	TextInfo textInfo = new TextInfo();
	// #1F69AA => 31, 105, 170
	textInfo.setColor(new Color((short)31, (short)105, (short)170));
	
	textInfo.setFontName("Arial Bold");
	textInfo.setFontSize(14f);
	
	Text text = new Text("Submittal");
	text.setTextInfo(textInfo);
	
	section.getParagraphs().add(text);
	pdf.save(filename);
	
	System.out.println("top: " + PAGE_TOP_MARGIN);
	System.out.println("left: " + PAGE_LEFT_MARGIN);
	System.out.println("right: " + PAGE_RIGHT_MARGIN);
	System.out.println("bottom: " + PAGE_BOTTOM_MARGIN);
	System.out.println(String.format("Created PDF [%s]", filename));
    }

}
