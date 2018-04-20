package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {
	
	private Integer limit;
	private boolean justify;
	private int start = 0;
	private int gap;

	public IdwallFormatter(Integer limit, Boolean justify) {
		this.limit = limit;
		this.justify = justify;
	}

	/**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
    	StringBuilder newText = new StringBuilder();
    	while(text.length() > start) {
    		gap = 0;
    		if(text.length() < (start + limit)) {
    			newText.append(lastLine(text, start));
    		} else {
    			newText.append(makingLine(text, start, start + limit) + "\n");
    		}
    	}
    	return newText.toString();
    }
    
	private String makingLine(String text, int startLine, int endLine) {
		if(Character.toString(text.charAt(startLine)).matches("[\\S]")) {
			if(text.substring(endLine - 1, endLine).matches("[\\s]") || text.substring(endLine, endLine + 1).matches("[\\s]")) {
				start = endLine;
				return justify && gap != 0 ? justifyLine(text.substring(startLine, endLine), gap) : text.substring(startLine, endLine);
			} else {
				gap++;
				return makingLine(text, startLine, endLine - 1);
			}
		} else {
			return makingLine(text, startLine + 1, endLine + 1);
		}
	}
	
	private String lastLine(String text, int startLine) {
		if(Character.toString(text.charAt(startLine)).matches("[\\S]")) {
			start = text.length();
			return text.substring(startLine);
		} else {
			return lastLine(text, startLine + 1);
		}
	}
	
	private String justifyLine(String line, int gap) {
		/**
		 * Magic
		 */
		return line;
	}
}
