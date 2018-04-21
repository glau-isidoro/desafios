package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {
	
	private Integer limit;
	private boolean justify;
	private int start = 0;

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
    		if(text.length() < (start + limit)) {
    			newText.append(lastLine(text, start));
    		} else {
    			newText.append(makeLine(text, start, start + limit) + "\n");
    		}
    	}
    	return newText.toString();
    }
    
	private String makeLine(String text, int startLine, int endLine) {
		if(Character.toString(text.charAt(startLine)).matches("[\\S]")) {
			if(text.substring(endLine - 1, endLine).matches("[\\s]") || text.substring(endLine, endLine + 1).matches("[\\s]")) {
				start = endLine;
				return justify ? justifyLine(text.substring(startLine, endLine)) : text.substring(startLine, endLine);
			} else {
				return makeLine(text, startLine, endLine - 1);
			}
		} else {
			return makeLine(text, startLine + 1, endLine + 1);
		}
	}
	
	private String lastLine(String text, int startLine) {
		if(Character.toString(text.charAt(startLine)).matches("[\\S]")) {
			start = text.length();
			return justify ? justifyLine(text.substring(startLine)) : text.substring(startLine);
		} else {
			return lastLine(text, startLine + 1);
		}
	}
	
	private String justifyLine(String line) {
		if(line.endsWith(" ")) {
			line = line.substring(0, line.length() - 1);
		}
		int i = line.length();
		while(line.length() < limit) {
			if(line.substring(i - 1, i).matches("[\\s]")) {
				line = line.substring(0, i).concat(" ").concat(line.substring(i));				
			}
			if(i > 1) {
				i--;
			} else {
				i = line.length();
			}
		}
		return line;
	}
}
