package com.bbva.capb.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.bbva.elara.batch.contextutils.ContextUtils;

public class Step1 implements Tasklet {

	private ContextUtils contextUtils;
	
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
	
		Logger log = contextUtils.getLOGGER(chunkContext, getClass());
		
		String rutaFicheroUno = 
				"C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\ListadoTarjetas2.txt";
		String rutaFicheroDos =
				"C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\ListProdAsoc.txt";		
		
		ordenarFicheroUno(rutaFicheroUno); 
		ordenarFicheroDos(rutaFicheroDos);
		
		 return null;
	}
	
	private void ordenarFicheroUno(String rutaFicheroUno) throws IOException, FileNotFoundException{
		ArrayList<String> array = new ArrayList<>();
		String linea = null;
		BufferedReader bf = new BufferedReader(new FileReader(rutaFicheroUno));
		while((linea=bf.readLine()) != null){
			String datos[] = linea.split(";");
			array.add((datos[2]+datos[3]+datos[4]+datos[5])+";"+datos[0]+";"+datos[1]+";"+datos[2]+";"
			+datos[3]+";" + datos[4]+";" +datos[5] +";"+datos[6]+";"
			+datos[7]+";"+datos[8]+";"+datos[9]);
		}
		 Collections.sort(array);
		 BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				 "C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\ListadoTarjetas2Ordenado.txt")));
		
		 for(int i =0; i<array.size();i++){
			 if(i==0){
				 bw.write(array.get(i));
			 }
			 bw.write("\n" + array.get(i));
		 }
	}
	
	private void ordenarFicheroDos(String rutaFicheroDos) throws IOException, FileNotFoundException{
		ArrayList<String> array = new ArrayList<>();
		String linea = null;
		BufferedReader bf = new BufferedReader(new FileReader(rutaFicheroDos));
		while((linea=bf.readLine()) != null){
			String datos[] = linea.split(";");		
			array.add((datos[0]+datos[1]+datos[2])+";"+datos[3]+";"+datos[4]+";"+datos[5]);
		}
		 Collections.sort(array);
		 BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				 "C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\ListProdAsocOrdenado.txt")));
		
		 for(int i =0; i<array.size();i++){
			 if(i==0){
				 bw.write(array.get(i));
			 }
			 bw.write("\n" + array.get(i));
		 }
	}
	
	public void setContextUtils(ContextUtils contextUtils) {
		this.contextUtils = contextUtils;
	}


}
