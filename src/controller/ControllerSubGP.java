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

public class ControllerSubGP implements ActionListener {
    private JTextField textFieldSubarea;
    private JTextArea textAreaCS;
    private JTextField textFieldNOMEGP;
    private JTextField textFieldCodgpCp;

    public ControllerSubGP(JTextField textFieldSubarea, JTextArea textAreaCS, JTextField textFieldNOMEGP, JTextField textFieldCodgpCp) {
        this.textFieldSubarea = textFieldSubarea;
        this.textAreaCS = textAreaCS;
        this.textFieldNOMEGP = textFieldNOMEGP;
        this.textFieldCodgpCp = textFieldCodgpCp;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Pesquisar")) {
            
                try {
					consultar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
            
    }

    private void consultar() throws Exception {
        Grupos g = new Grupos();
        Lista l = new Lista();
        g.subarea = textFieldSubarea.getText();
        

        l = buscaGrupo(g.subarea);
        
        int tm = l.size();
   	 if(l!=null) {
   			
   			StringBuffer buffer = new StringBuffer();
   			if(tm>0) {
   			for(int i = 0 ; i< tm ;i++) {
   				Grupos grupo= (Grupos)l.get(i);
   				buffer.append("Subárea: " + grupo.subarea + " - Código: " + grupo.codigo + " - Nome: " + grupo.nome);
        }
   		  textAreaCS.setText(buffer.toString());
   			}else {
            textAreaCS.setText(" v");
        }
    }
   	 }
    

    private Lista buscaGrupo(String subarea) throws IOException {
    	 Grupos grupo=new Grupos();
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
					if(vetLinha[3].contains(subarea)) {
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
						System.out.println(vetLinha[3]);
						
						l.addFirst(grupo);
					}
					
					}
					linha=buffer.readLine();

				}           
				buffer.close();
				isr.close();
				fis.close();
			}
			if(l.isEmpty()==true) {
				l.addFirst(" vazio");
			}
			return l;
		}
}

