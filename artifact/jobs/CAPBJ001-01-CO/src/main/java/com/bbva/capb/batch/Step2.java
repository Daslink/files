package com.bbva.capb.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.bbva.elara.batch.contextutils.ContextUtils;

public class Step2 implements Tasklet {

	private ContextUtils contextUtils;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// Logger log = contextUtils.getLOGGER(chunkContext, getClass());

		String rutaFicheroUno = "C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\ListadoTarjetas2Ordenado.txt";
		String rutaFicheroDos = "C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\ListProdAsocOrdenado.txt";

		leerFicheros(rutaFicheroUno, rutaFicheroDos);

		return null;
	}

	private void leerFicheros(String rutaUno, String rutaDos) throws IOException, FileNotFoundException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"C:\\Users\\hdramos\\Downloads\\IDE_APX_win64\\IDE_APX_win64\\workspace\\UF_BATCH_CAPB\\lineaBalance.txt")));
		BufferedReader bfUno = new BufferedReader(new FileReader(rutaUno));
		BufferedReader bfDos = new BufferedReader(new FileReader(rutaDos));

		String lineaUno = bfUno.readLine();
		String lineaDos = bfDos.readLine();

		while ((lineaUno) != null && (lineaDos) != null) {
			String datosUno[] = lineaUno.split(";");
			String datosDos[] = lineaDos.split(";");
			if (datosUno[0].equals(datosDos[0])) {
				bw.write(datosUno[3] + ";" + datosUno[4] + ";" + datosDos[1] + ";" + datosDos[2] + ";"
						+ (datosUno[1] + datosUno[2] + ";" + validarTarjetaAsociada(datosDos[3], datosUno[2])) + "\n");
				lineaUno = bfUno.readLine();
				lineaDos = bfDos.readLine();
			} else {
				if (Long.parseLong(datosUno[0]) > Long.parseLong(datosDos[0])) {
					lineaDos = bfDos.readLine();
				} else {
					if (Long.parseLong(datosUno[0]) < Long.parseLong(datosDos[0])) {
						lineaUno = bfUno.readLine();
					}
				}
			}
		}
	}

	private String validarTarjetaAsociada(String tarjetaAsociada, String IAI) {
		if (tarjetaAsociada.contains("A")) {
			tarjetaAsociada = tarjetaAsociada.replace('A', tarjetaAsociada.charAt(tarjetaAsociada.indexOf("A") + 1));
			return tarjetaAsociada;
		}
		if (tarjetaAsociada.contains("B")) {
			tarjetaAsociada = tarjetaAsociada.replace('B', tarjetaAsociada.charAt(tarjetaAsociada.indexOf("B") - 1));
			return tarjetaAsociada;
		}
		if (tarjetaAsociada.contains("X")) {
			tarjetaAsociada = tarjetaAsociada.replace('X', IAI.charAt(IAI.length() - 1));
			return tarjetaAsociada;
		}
		if (tarjetaAsociada.contains("W")) {
			tarjetaAsociada = tarjetaAsociada.replace('W', IAI.charAt(0));
			return tarjetaAsociada;
		}
		if (tarjetaAsociada.contains("Z")) {
			tarjetaAsociada = tarjetaAsociada.replace('Z', '9');
			return tarjetaAsociada;
		}

		return tarjetaAsociada;
	}

	public void setContextUtils(ContextUtils contextUtils) {
		this.contextUtils = contextUtils;
	}

}
