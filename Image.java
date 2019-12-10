package hm;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Image extends Employee {

//    public static void main(String avg[]) throws IOException
//    {
//        DisplayImage abc=new DisplayImage("/Users/JB/Desktop/batman.jpg");
//    }

    public static void show(String path) throws IOException
    {
        BufferedImage img=ImageIO.read(new File(path));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public static String addImg() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		  //System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
		  //System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
		} else {
		  System.out.println("No Selection ");
		}
		String path = chooser.getSelectedFile().toString();
		//System.out.println(path);
		return path;
	}
}
