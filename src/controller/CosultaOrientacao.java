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

import Model2.Orientacao;
import br.com.mary.pilhastring.Pilha;
import model.Lista;

public class CosultaOrientacao implements ActionListener {
	private JTextField textFieldCodigoOrientacao;
	private  JTextArea textAreaConsultaOrientacao;
	



	public CosultaOrientacao(JTextField textFieldCodigoOrientacao, JTextArea textAreaConsultaOrientacao) {
		
		this.textFieldCodigoOrientacao = textFieldCodigoOrientacao;
		this.textAreaConsultaOrientacao = textAreaConsultaOrientacao;
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Ultima")) {
			
				try {
					Consulta();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		if(cdm.equals("Pesquisar")) {
			try {
				buscalista();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
	}
	private void buscalista() throws Exception {
		Orientacao orientacao=new Orientacao();
		orientacao.codOrientacao=textFieldCodigoOrientacao.getText();
		Lista l = new Lista();
		l = BuscaOrLista(orientacao.codOrientacao);
		int tm = l.size();
		StringBuffer buffer= new StringBuffer();
		if(tm>0) {
		for(int i=0 ; i<tm ;i++) {
			buffer.append(l.get(i));
			buffer.append("\n\r" );
			
		}
		textAreaConsultaOrientacao.setText(buffer.toString());
		
	}
	}



	private Lista BuscaOrLista(String codOrientacao) throws IOException {
		Lista l = new Lista ();
		Orientacao orientacao=new Orientacao();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[1].equals(codOrientacao)) {
					orientacao.codOrientacao=vetLinha[1];
					orientacao.codigoGP=vetLinha[2];
					orientacao.titulo=vetLinha[0];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];
                    l.addFirst(orientacao.descricao);
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
	



	private void Consulta() throws Exception {
		Orientacao orientacao=new Orientacao();
		orientacao.codOrientacao=textFieldCodigoOrientacao.getText();
		 Pilha p = new Pilha();
		
			p=buscaCodorientacao(orientacao.codOrientacao);	
			textAreaConsultaOrientacao.setText(p.exibir());
			
		}



	


	private Pilha buscaCodorientacao(String codOrientacao) throws IOException {
		Pilha p = new Pilha();
		Orientacao orientacao=new Orientacao();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[1].equals(codOrientacao)) {
					orientacao.codOrientacao=vetLinha[1];
					orientacao.codigoGP=vetLinha[2];
					orientacao.titulo=vetLinha[0];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];
                    p.push(orientacao.descricao);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		if(p.isEmpty()==true) {
			p.push(null);
		}
		return p;
	}	
	
}
	



	