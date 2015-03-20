package WaveTune.WaveTune;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AudioParser {

	private Metadata metadata=null;
	public String getTitle() {
		return metadata.get("title");
	}

	public String getArtiste() {
		return metadata.get("xmpDM:artist");
	}

	public String getGenre() {
		return metadata.get("xmpDM:genre");
	}

	public String getAlbum() {
		return metadata.get("xmpDM:album");
	}

	public AudioParser(String name){
		try{
			InputStream input = new FileInputStream(new File(name));
			ContentHandler handler = new DefaultHandler();
			this.metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}
}