package tecnico.ssof.project;

import tecnico.ssof.project.parser.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class ParserFacade {

	private static final String ENCODING = "UTF-8";
	
	public ParserFacade() {
		
	}
	
	/// Parse the given file
	///
	/// @param file - file to parse
	/// @return: the root RuleContext (first rule of the grammar)
	public PHPParser.HtmlDocumentContext parse(File file) throws IOException {
		
        String code = readFile(file, Charset.forName(ENCODING));
        
        // create lexer and give it the code
        PHPLexer lexer = new PHPLexer(new ANTLRInputStream(code));
        
        // get tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // create parser and give it the tokens
        PHPParser parser = new PHPParser(tokens);

        // parse starting at rule htmlDocument
        return parser.htmlDocument();
	}
	
	/// get String from File
	///
	/// @param file - a file
	/// @param encoding - encoding from file
	/// @return: the content of the file as a String
	private static String readFile(File file, Charset encoding) throws IOException {
		
        byte[] encoded = Files.readAllBytes(file.toPath());
        
        return new String(encoded, encoding);
    }
}
