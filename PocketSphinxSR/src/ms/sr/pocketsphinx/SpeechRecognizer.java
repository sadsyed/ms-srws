package ms.sr.pocketsphinx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class SpeechRecognizer {
	private String result;
	private boolean initial= true; 
	//String outputFile = "/home/parallels/workspace/output.txt";
	String outputFile = "/home/ubuntu/output.txt";

	public String getResult() {
		recognizedOutput();
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
	
	public String recognize(){
	    initial = false;

		String lm = new String("/home/ubuntu/downloads/hobbit/4393.lm");
		String dict = new String("/home/ubuntu/downloads/hobbit/4393.dic");
		String wavFile = new String("/home/ubuntu/downloads/charlieHobbit.wav");
		String outputFile = "/home/ubuntu/output.txt";
		
		/*String lm = new String("/home/parallels/workspace/hobbit-lm/0901.lm");
		String dict = new String("/home/parallels/workspace/hobbit-lm/0901.dic");
		String wavFile = new String("/home/parallels/workspace/charlieHobbit.wav");
		String outputFile = "/home/parallels/workspace/output.txt";*/

		StringBuilder cmd = new StringBuilder();
		
		cmd.append("pocketsphinx_continuous ").append("-infile ").append(wavFile)
		.append(" -lm ").append(lm).append(" -dict ").append(dict).append(" | tee ")
		.append(outputFile);
		
		System.out.println(" ---- " + cmd);  
		
		try {
			String simpleCmd = "pocketsphinx_continuous";

            // using the Runtime exec method:
			String stdOutput = null;
			
			//File currentDirectory = new File(new File(".").getAbsolutePath());
			//System.out.println(currentDirectory.getCanonicalPath());
			
			String myCurrentDir = Paths.get(".").toAbsolutePath().normalize().toString();
			System.out.println("Current Directory : " + myCurrentDir);
			
			String webAppsDir ="/webapps/PocketSphinxSR";
			
			String [] shCmd = new String[]{"/bin/sh", myCurrentDir + webAppsDir + "/scripts/pocketsphinx.sh"};
			Process proc = Runtime.getRuntime().exec(shCmd);
            //Process proc = Runtime.getRuntime().exec(cmd.toString());
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(proc.getErrorStream()));

            // get the output from the pocketsphinx_continuous command
            System.out.println("Output of pocketsphinx_continuous command:\n");
            while ((stdOutput = stdInput.readLine()) != null) {
                System.out.println(stdOutput);
            }
            
            // get any errors from the pocketsphinx_continuous command
            System.out.println("Error pocketsphinx_continuous command:\n");
            while ((stdOutput = stdError.readLine()) != null) {
                System.out.println(stdOutput);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	
		return null;
	}
	
	public void recognizedOutput(){
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
	}
}
