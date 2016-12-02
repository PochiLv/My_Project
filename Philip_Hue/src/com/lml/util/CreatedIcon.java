package com.lml.util;
import java.net.URL;
import javax.swing.ImageIcon;
//import vehicle.main.Main;;

import com.lml.main.Main;

public class CreatedIcon {
	public static ImageIcon add(String ImageName){
		URL IconUrl = Main.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
