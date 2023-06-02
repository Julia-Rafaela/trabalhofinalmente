package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextField;

import Model2.Aluno;
import Model2.Grupos;
import model.Lista;
public class GruposController implements ActionListener {
	private  JTextField textFieldNOMEGP;
	private  JTextField textFieldTEMAGP;
	private  JTextField textFieldAREA;
	private  JTextField textFieldSUBAREA;
	private  JTextField textFieldDATA;
	private  JTextField textFieldCOD;
	private  JTextField textField_ra01;
	private  JTextField textField_ra02;
	private  JTextField textField_ra03;
	private  JTextField textField_ra04;
	private  JTextField textGruponome1;
	private  JTextField textGruponome2;
	private  JTextField textGruponome3;
	private  JTextField textGruponome4;
	
	
	
	public GruposController(JTextField textFieldNOMEGP, JTextField textFieldTEMAGP, JTextField textFieldAREA,
			JTextField textFieldSUBAREA, JTextField textFieldDATA, JTextField textFieldCOD,
			JTextField textField_ra01, JTextField textField_ra02, JTextField textField_ra03, JTextField textField_ra04,JTextField textGruponome1,JTextField textGruponome2,JTextField textGruponome3,
			JTextField textGruponome4) {
		
	
		this.textFieldNOMEGP = textFieldNOMEGP;
		this.textFieldTEMAGP = textFieldTEMAGP;
		this.textFieldAREA = textFieldAREA;
		this.textFieldSUBAREA = textFieldSUBAREA;
		this.textFieldDATA = textFieldDATA;
		this.textFieldCOD = textFieldCOD;
		this.textField_ra01 = textField_ra01;
		this.textField_ra02 = textField_ra02;
		this.textField_ra03 = textField_ra03;
		this.textField_ra04 = textField_ra04;
		this.textGruponome1 = textGruponome1;
		this.textGruponome2 = textGruponome2;
		this.textGruponome3 = textGruponome3;
		this.textGruponome4 =textGruponome4;
		}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Cadastrar")) {
				try {
					cadastro();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
	}
	



	


	private Lista BuscarAluno(String ra1) throws IOException {
		Lista l = new Lista ();
	Aluno a = new Aluno ();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Aluno.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[0].equals(a.Ra)) {
					a.Ra = vetLinha[0];
					a.Nome = vetLinha[1];
					l.addFirst(a);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		if(l.isEmpty()==true) {
			l.addFirst(null);
		}
		return l;
	}	
				
	



	private void cadastro() throws IOException {
		Grupos grupo=new Grupos();
		grupo.nome=textFieldNOMEGP.getText();
		grupo.tema=textFieldTEMAGP.getText();
		grupo.area=textFieldAREA.getText();
		grupo.subarea=textFieldSUBAREA.getText();
		grupo.data=textFieldDATA.getText();
		grupo.codigo=textFieldCOD.getText();
		grupo.Ra1 = textField_ra01.getText();
		grupo.Ra2 = textField_ra02.getText();
		grupo.Ra3 = textField_ra03.getText();
		grupo.Ra4 = textField_ra04.getText();
		grupo.nome1=textGruponome1.getText();
		grupo.nome2=textGruponome2.getText();
		grupo.nome3=textGruponome3.getText();
		grupo.nome4 = textGruponome4.getText();
	    System.out.println(grupo);
		
		Cadastragrupos(grupo.toString());
		textFieldNOMEGP.setText("");
		textFieldTEMAGP.setText("");
		textFieldAREA.setText("");
		textFieldSUBAREA.setText("");
		textFieldDATA.setText("");
		textFieldCOD.setText("");
		textField_ra01.setText("");
		textField_ra02.setText("");
		textField_ra03.setText("");
		textField_ra04.setText("");
		textGruponome1.setText("");
		textGruponome2.setText("");
		textGruponome3.setText("");
		textGruponome4.setText("");
		}



	private void Cadastragrupos(String csvGrupos) throws IOException {
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
			File dir= new File(path);
			if(!dir.exists()) {
				dir.mkdir();
				
			}
			File arq= new File(path, "grupos.csv");
			boolean existe=false;
			if(arq.exists()) {
				existe=true;
			}
			FileWriter fw= new FileWriter(arq, existe);
			
			PrintWriter pw= new PrintWriter(fw);
			pw.write(csvGrupos+"\r\n");
			pw.flush();
			pw.close();
			fw.close();
			
			
		}




}
