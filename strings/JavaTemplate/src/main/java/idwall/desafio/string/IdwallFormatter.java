package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {
	
	private Integer limit;
	private int start = 0;

	public IdwallFormatter(Integer limit) {
		this.limit = limit - 1;
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
    		if(text.length() < start + limit) {
    			newText.append(makingLine(text, start, text.length() - 1));
    		} else {
    			newText.append(makingLine(text, start, start + limit) + "\n");
    		}
    	}
    	return newText.toString();
    }
    
	private String makingLine(String text, int startLine, int endLine) {
		if(Character.toString(text.charAt(startLine)).matches("[a-zA-Z]")) {
			if(text.length() == endLine || text.substring(endLine, endLine + 1).matches("[^a-zA-Z\",.]") || text.substring(endLine + 1, endLine + 2).matches("[^a-zA-Z\",.]")) {
				start = endLine + 1;
				return text.substring(startLine, endLine);
			} else {
				return makingLine(text, startLine, endLine - 1);
			}
		} else {
			return makingLine(text, startLine + 1, endLine + 1);
		}
	}
}
