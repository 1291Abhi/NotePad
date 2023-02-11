import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;


class NotePad extends JFrame implements ActionListener {
    // fram declaration
    JFrame frame;
    //Text area declaration
    JTextArea textArea;

    NotePad(){
        //intialising the frame
        frame=new JFrame("NotePad");

        //intialising the textarea
        textArea=new JTextArea("Welcome to NotePad");


        //create MenuBar
        JMenuBar menuBar=new JMenuBar();


        //creating file menu
        JMenu file=new JMenu("File");



        //creating the option for File menubar
        JMenuItem f1=new JMenuItem("New");
        JMenuItem f2=new JMenuItem("Save");
        JMenuItem f3=new JMenuItem("Open");
        JMenuItem f4=new JMenuItem("Print");

        //add actionlisteners to each of the options
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding menuitem to "File" menu
        // adding the option to the file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);



        //creating "Edit" menu
        JMenu edit=new JMenu("Edit");

        //creating the option for Edit menu Bar
        JMenuItem e1=new JMenuItem("Cut");
        JMenuItem e2=new JMenuItem("Copy");
        JMenuItem e3=new JMenuItem("Paste");

        //add actionListeners to each of the option of edit menuitem
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        //Adding menuitem to "Edit" menu
        //adding option to edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);



        //creating "Close" Menu
        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);


        // adding file,edit,close to MenuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(close);


        //setting evrything to frame
        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }

    //to add the finctionalities
    public void actionPerformed(ActionEvent e){

        //extracting command into to string
        String s=e.getActionCommand();
        switch(s){
            case "New":
                textArea.setText("");
                break;
            case "Save":
                ////code for savng txt file to memory
                //creating the eobeject of jfilechooser class with starting path of D:
                JFileChooser j=new JFileChooser("D:");

                //invoke save dialoguebox
                int r=j.showSaveDialog(null);
                //r contains status of dialogbox 0 if clicked on save
                if(r==0){
                    //declare a file object and store file location
                    File fi=new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter fw=new FileWriter(fi);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(textArea.getText());

                        bw.flush();
                        bw.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }
                else{
                    JOptionPane.showMessageDialog(frame,"The user has cancelled the operation");
                }

                break;
            case "Open":
                JFileChooser jo=new JFileChooser("D:");

                //invoke save dialoguebox
                int ro=jo.showOpenDialog(null);
                //r contains status of dialogbox 0 if clicked on save
                if(ro==0){
                    //declare a file object and store file location
                    File fi=new File(jo.getSelectedFile().getAbsolutePath());

                    try {
                        FileReader fw=new FileReader(fi);
                        BufferedReader bw=new BufferedReader(fw);
                        String s1="",s2="";
                        //first Line stored int s1;
                        s1=bw.readLine();
                        while(s1!=null)
                        {
                            s2+=s1+"\n";
                            s1=bw.readLine();
                        }
                        textArea.setText(s2);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }
                else{
                    JOptionPane.showMessageDialog(frame,"The user has cancelled the operation");
                }
                break;
            case "Print":
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
            case "Close":
                frame.setVisible(false);
                break;


        }

    }


    public static void main(String args[]) {
        NotePad n = new NotePad();
    }
}