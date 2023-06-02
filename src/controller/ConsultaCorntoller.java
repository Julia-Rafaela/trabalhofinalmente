package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model2.Grupos;
import model.Lista;

public class ConsultaCorntoller implements ActionListener {
	 private JTextField  textFieldCodgpCp;
	
		private JTextArea textAreaCG;
		
	


	public ConsultaCorntoller(JTextField textFieldCodgpCp,  JTextArea textAreaCG ) {
		
	this.textFieldCodgpCp = textFieldCodgpCp; // onde digita o código do grupo
		
	this.textAreaCG = textAreaCG;
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Pesquisar")) {
			
					try {
						Consulta();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
				
		
	}
	private void Consulta() throws Exception {
		Grupos g=new Grupos();
				
		g.codigo=textFieldCodgpCp.getText();
		
	Lista l=new Lista();
	 l = BuscarGrupo(g.codigo);
	 
	 if(l!=null) {
		 int tm = l.size();
			StringBuffer buffer = new StringBuffer();
			if(tm>0) {
			for(int i = 0 ; i< tm ;i++) {
				Grupos g1 = (Grupos)l.get(i);
				
				buffer.append("Cod " + g1.codigo+ " Nome do Grupo "+ g1.nome+" Tema: "+g1.tema + " SubArea: "+ g1.subarea+ "Area:" +g1.area+ " Dta Formção:" + g1.data);
				buffer.append("\n\r");
				buffer.append(" RA1: "+ g1.Ra1+ "  Nome:  "+ g1.nome1+ " RA2: "+ g1.Ra2+ "  Nome:  "+ g1.nome2 + " RA3: "+ g1.Ra3+ "  Nome:  "+ g1.nome3+ " RA4: "+ g1.Ra4+ "  Nome:  "+ g1.nome4);
				buffer.append("\n\r");
		
			}
			textAreaCG.setText(buffer.toString());
			
			}
			}else {
				textAreaCG.setText("Grupo não encontrado");
			
			}
	 }
	

	 

	 
	
	private Lista BuscarGrupo(String codigo) throws IOException {
		
		 Lista l = new Lista();
			String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
			File arq= new File(path, "grupos.csv");
			if(arq.exists() && arq.isFile()) {
				FileInputStream fis=new FileInputStream(arq);
				InputStreamReader isr= new InputStreamReader(fis);
				BufferedReader buffer= new BufferedReader(isr);
				String linha=buffer.readLine();
				while(linha!=null) {
					String[] vetLinha=linha.split(";");
					if (vetLinha.length > 1) {
					if(vetLinha[5].contains(codigo)) {
						 Grupos grupo=new Grupos();
						grupo.nome =vetLinha[0];
						grupo.tema=vetLinha[1];
						grupo.area=vetLinha[2];
						grupo.subarea=vetLinha[3];
						grupo.data=vetLinha[4];
						grupo.codigo=vetLinha[5];
						grupo.Ra1=vetLinha[6];
						grupo.nome1= vetLinha[7];
						grupo.Ra2=vetLinha[8];
						grupo.nome2= vetLinha[9];
						grupo.Ra3=vetLinha[10];
						grupo.nome3= vetLinha[11];
						grupo.Ra4=vetLinha[12];
						grupo.nome4= vetLinha[13];
						
						l.addFirst(grupo);
					}
					
					}
					linha=buffer.readLine();

				}           
				buffer.close();
				isr.close();
				fis.close();
			}
			
			return l;
		}
}
	
	
