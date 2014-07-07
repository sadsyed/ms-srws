package ms.sr.pocketsphinx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LanguageModelGenerator {
	private String result;
	private boolean initial= true; 
	//String outputFile = "/home/parallels/workspace/output.txt";
	String outputFile = "/home/ubuntu/output.txt";
	private final String UPLOAD_DIRECTORY = "/home/ubuntu/apache-tomcat-7.0.54/data";
	private final String ARGUMENT_SPACE = " ";
	private final String FORWARD_SLASH = "/";
	
	private String name = "No name specified";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void init(){
		//File jspFile = new File(Paths.get(".").toAbsolutePath().normalize().toString());
		//File dir = jspFile.getParentFile();

	}
	
	public String generate(){
		System.out.println("------- Generate -------");
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	    initial = false;
		
		try {
            // using the Runtime exec method:
			String stdOutput = null;
			
			String myCurrentDir = Paths.get(".").toAbsolutePath().normalize().toString();
			System.out.println("Current Directory : " + myCurrentDir);
			
			//String nameOfLM = (String) session.getAttribute("lmName");
			//System.out.println("Language Model Name: " + nameOfLM);
			System.out.println("Language Model Name: " + name);
			
			String webAppsDir ="/webapps/PocketSphinxSR";
			StringBuilder cmdBuilder = new StringBuilder();
			cmdBuilder.append(myCurrentDir)
									.append(webAppsDir)
									.append("/scripts/generate-lm.sh");
			
			System.out.println(cmdBuilder.toString());
			
			String [] shCmd = new String[]{"/bin/sh", 
																			cmdBuilder.toString(), UPLOAD_DIRECTORY+"/yastasheer.txt",
																			name};
			
			System.out.println(" ---- " + shCmd);  
			
			Process proc = Runtime.getRuntime().exec(shCmd);
            //Process proc = Runtime.getRuntime().exec(cmd.toString());
			
			result = "LM generated";
			
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(proc.getErrorStream()));

            // get the output from the pocketsphinx_continuous command
            System.out.println("Output of generate lm command:\n");
            while ((stdOutput = stdInput.readLine()) != null) {
                System.out.println(stdOutput);
            }
            
            // get any errors from the pocketsphinx_continuous command
            System.out.println("Error generate lm command:\n");
            while ((stdOutput = stdError.readLine()) != null) {
                System.out.println(stdOutput);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	
		System.out.println("Result: " + result);
		return null;
	}
	
	/*public void recognizedOutput(){
		StringBuilder fileOutput = null;
		
		BufferedReader br = null;
		try{
			String currentLine;
			br = new BufferedReader(new FileReader(outputFile));
			fileOutput = new StringBuilder();
			
			while((currentLine = br.readLine()) != null) {
				fileOutput.append(currentLine).append("\n");
				System.out.println(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fileOutput != null) {
			result = fileOutput.toString();
		} else
			result = "Speech Recognition failed.";
	}*/
	
	public String getResult() {
		//recognizedOutput();
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public boolean getInitial() {
		return initial;
	}

	public void setInitial(boolean initial) {
		this.initial = initial;
	}
}
