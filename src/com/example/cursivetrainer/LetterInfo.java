 
/** LetterInfo.java
 * 
 * Class that represents a letter (both lower and upper case) as well its information for displaying info on the screen
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class LetterInfo
{
	char letter;
	String[] info;
	int resourceString;
	String imageString;
	String videoURI;
	//final char[] clockLetters = {'a','c','o','d','g','q'};
	//final char[] hillLetters = {'n','m','v','x','y','z'};
	//final char[] loopLetters = {'e','l','b','f','h','k'};
	//final char[] lineLetters = {'i','t','j','p','r','s', 'u', 'w'};
	final int[] clockLetters = {0, 2, 14, 3, 6, 16};
	final int[] hillLetters = {13, 12, 21, 23, 24, 25};
	final int[] loopLetters = {4, 11, 1, 5, 7, 10};
	final int[] lineLetters = {8, 19, 9, 15, 17, 18, 20, 22};
	
	private static final String TAG = "LetterInfo";
	
	private final int INVALID_LETTER = 255;
	private final int INVALID_RESOURCE = -1;
	
	public LetterInfo()
	{
		this.letter = INVALID_LETTER;
		this.info = null;
		this.imageString = null;
		this.resourceString = INVALID_RESOURCE;
	}
	
	public LetterInfo(char letter, String[] info)
	{
		this.letter = letter;
		this.info = info;
	}
	
	public void determineLowerLetter(int num, Resources res) throws Exception
	{
		switch(num)
		{
		case 0:
			this.info = res.getStringArray(R.array.a_instructions);
			this.letter = 'a';
			this.resourceString = R.raw.a;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/a.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/al.mp4";
			break;
		case 1:
			this.info = res.getStringArray(R.array.b_instructions);
			this.letter = 'b';
			this.resourceString = R.raw.b;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/b.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/bl.mp4";
			break;
		case 2:
			this.info = res.getStringArray(R.array.c_instructions);
			this.letter = 'c';
			this.resourceString = R.raw.c;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/c.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/cl.mp4";
			break;
		case 3:
			this.info = res.getStringArray(R.array.d_instructions);
			this.letter = 'd';
			this.resourceString = R.raw.d;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/d.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/dl.mp4";
			break;
		case 4:
			this.info = res.getStringArray(R.array.e_instructions);
			this.letter = 'e';
			this.resourceString = R.raw.e;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/e.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/el.mp4";
			break;
		case 5:
			this.info = res.getStringArray(R.array.f_instructions);
			this.letter = 'f';
			this.resourceString = R.raw.f;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/f.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/fl.mp4";
			break;
		case 6:
			this.info = res.getStringArray(R.array.g_instructions);
			this.letter = 'g';
			this.resourceString = R.raw.g;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/g.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/gl.mp4";
			break;
		case 7:
			this.info = res.getStringArray(R.array.h_instructions);
			this.letter = 'h';
			this.resourceString = R.raw.h;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/h.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/hl.mp4";
			break;
		case 8:
			this.info = res.getStringArray(R.array.i_instructions);
			this.letter = 'i';
			this.resourceString = R.raw.i;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/i.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/il.mp4";
			break;
		case 9:
			this.info = res.getStringArray(R.array.j_instructions);
			this.letter = 'j';
			this.resourceString = R.raw.j;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/j.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/jl.mp4";
			break;
		case 10:
			this.info = res.getStringArray(R.array.k_instructions);
			this.letter = 'k';
			this.resourceString = R.raw.k;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/k.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/kl.mp4";
			break;
		case 11:
			this.info = res.getStringArray(R.array.l_instructions);
			this.letter = 'l';
			this.resourceString = R.raw.l;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/l.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/ll.mp4";
			break;
		case 12:
			this.info = res.getStringArray(R.array.m_instructions);
			this.letter = 'm';
			this.resourceString = R.raw.m;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/m.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/ml.mp4";
			break;
		case 13:
			this.info = res.getStringArray(R.array.n_instructions);
			this.letter = 'n';
			this.resourceString = R.raw.n;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/n.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/nl.mp4";
			break;
		case 14:
			this.info = res.getStringArray(R.array.o_instructions);
			this.letter = 'o';
			this.resourceString = R.raw.o;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/o.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/ol.mp4";
			break;
		case 15:
			this.info = res.getStringArray(R.array.p_instructions);
			this.letter = 'p';
			this.resourceString = R.raw.p;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/p.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/pl.mp4";
			break;
		case 16:
			this.info = res.getStringArray(R.array.q_instructions);
			this.letter = 'q';
			this.resourceString = R.raw.q;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/qu.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/qul.mp4";
			break;
		case 17:
			this.info = res.getStringArray(R.array.r_instructions);
			this.letter = 'r';
			this.resourceString = R.raw.r;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/r.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/rl.mp4";
			break;
		case 18:
			this.info = res.getStringArray(R.array.s_instructions);
			this.letter = 's';
			this.resourceString = R.raw.s;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/s.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/sl.mp4";
			break;
		case 19:
			this.info = res.getStringArray(R.array.t_instructions);
			this.letter = 't';
			this.resourceString = R.raw.t;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/t.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/tl.mp4";
			break;
		case 20:
			this.info = res.getStringArray(R.array.u_instructions);
			this.letter = 'u';
			this.resourceString = R.raw.u;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/u.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/ul.mp4";
			break;
		case 21:
			this.info = res.getStringArray(R.array.v_instructions);
			this.letter = 'v';
			this.resourceString = R.raw.v;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/v.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/vl.mp4";
			break;
		case 22:
			this.info = res.getStringArray(R.array.w_instructions);
			this.letter = 'w';
			this.resourceString = R.raw.w;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/w.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/wl.mp4";
			break;
		case 23:
			this.info = res.getStringArray(R.array.x_instructions);
			this.letter = 'x';
			this.resourceString = R.raw.x;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/x.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/xl.mp4";
			break;
		case 24:
			this.info = res.getStringArray(R.array.y_instructions);
			this.letter = 'y';
			this.resourceString = R.raw.y;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/y.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/yl.mp4";
			break;
		case 25:
			this.info = res.getStringArray(R.array.z_instructions);
			this.letter = 'z';
			this.resourceString = R.raw.z;
			this.imageString = "https://dl.dropboxusercontent.com/u/276232652/z.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276232652/zl.mp4";
			break;
		default:
			this.info = null;
			throw new Exception();
		}
	}
	
	public void determineUpperLetter(int num, Resources res) throws Exception
	{
		switch(num)
		{
		case 0:
			this.info = res.getStringArray(R.array.upper_a_instructions);
			this.letter = 'A';
			this.resourceString = R.raw.upper_a;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/ac.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/ac.mp4";
			break;
		case 1:
			this.info = res.getStringArray(R.array.upper_b_instructions);
			this.letter = 'B';
			this.resourceString = R.raw.upper_b;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/bc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/bc.mp4";
			break;
		case 2:
			this.info = res.getStringArray(R.array.upper_c_instructions);
			this.letter = 'C';
			this.resourceString = R.raw.upper_c;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/cc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/cc.mp4";
			break;
		case 3:
			this.info = res.getStringArray(R.array.upper_d_instructions);
			this.letter = 'D';
			this.resourceString = R.raw.upper_d;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/dc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/dc.mp4";
			break;
		case 4:
			this.info = res.getStringArray(R.array.upper_e_instructions);
			this.letter = 'E';
			this.resourceString = R.raw.upper_e;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/ec.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/ec.mp4";
			break;
		case 5:
			this.info = res.getStringArray(R.array.upper_f_instructions);
			this.letter = 'F';
			this.resourceString = R.raw.upper_f;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/fc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/fc.mp4";
			break;
		case 6:
			this.info = res.getStringArray(R.array.upper_g_instructions);
			this.letter = 'G';
			this.resourceString = R.raw.upper_g;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/gc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/gc.mp4";
			break;
		case 7:
			this.info = res.getStringArray(R.array.upper_h_instructions);
			this.letter = 'H';
			this.resourceString = R.raw.upper_h;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/hc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/hc.mp4";
			break;
		case 8:
			this.info = res.getStringArray(R.array.upper_i_instructions);
			this.letter = 'I';
			this.resourceString = R.raw.upper_i;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/ic.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/ic.mp4";
			break;
		case 9:
			this.info = res.getStringArray(R.array.upper_j_instructions);
			this.letter = 'J';
			this.resourceString = R.raw.upper_j;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/jc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/jc.mp4";
			break;
		case 10:
			this.info = res.getStringArray(R.array.upper_k_instructions);
			this.letter = 'K';
			this.resourceString = R.raw.upper_k;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/kc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/kc.mp4";
			break;
		case 11:
			this.info = res.getStringArray(R.array.upper_l_instructions);
			this.letter = 'L';
			this.resourceString = R.raw.upper_l;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/lc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/lc.mp4";
			break;
		case 12:
			this.info = res.getStringArray(R.array.upper_m_instructions);
			this.letter = 'M';
			this.resourceString = R.raw.upper_m;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/mc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/mc.mp4";
			break;
		case 13:
			this.info = res.getStringArray(R.array.upper_n_instructions);
			this.letter = 'N';
			this.resourceString = R.raw.upper_n;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/nc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/nc.mp4";
			break;
		case 14:
			this.info = res.getStringArray(R.array.upper_o_instructions);
			this.letter = 'O';
			this.resourceString = R.raw.upper_o;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/oc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/oc.mp4";
			break;
		case 15:
			this.info = res.getStringArray(R.array.upper_p_instructions);
			this.letter = 'P';
			this.resourceString = R.raw.upper_p;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/pc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/pc.mp4";
			break;
		case 16:
			this.info = res.getStringArray(R.array.upper_q_instructions);
			this.letter = 'Q';
			this.resourceString = R.raw.upper_q;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/qc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/qc.mp4";
			break;
		case 17:
			this.info = res.getStringArray(R.array.upper_r_instructions);
			this.letter = 'R';
			this.resourceString = R.raw.upper_r;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/rc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/rc.mp4";
			break;
		case 18:
			this.info = res.getStringArray(R.array.upper_s_instructions);
			this.letter = 'S';
			this.resourceString = R.raw.upper_s;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/sc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/sc.mp4";
			break;
		case 19:
			this.info = res.getStringArray(R.array.upper_t_instructions);
			this.letter = 'T';
			this.resourceString = R.raw.upper_t;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/tc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/tc.mp4";
			break;
		case 20:
			this.info = res.getStringArray(R.array.upper_u_instructions);
			this.letter = 'U';
			this.resourceString = R.raw.upper_u;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/uc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/uc.mp4";
			break;
		case 21:
			this.info = res.getStringArray(R.array.upper_v_instructions);
			this.letter = 'V';
			this.resourceString = R.raw.upper_v;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/vc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/vc.mp4";
			break;
		case 22:
			this.info = res.getStringArray(R.array.upper_w_instructions);
			this.letter = 'W';
			this.resourceString = R.raw.upper_w;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/wc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/wc.mp4";
			break;
		case 23:
			this.info = res.getStringArray(R.array.upper_x_instructions);
			this.letter = 'X';
			this.resourceString = R.raw.upper_x;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/xc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/xc.mp4";
			break;
		case 24:
			this.info = res.getStringArray(R.array.upper_y_instructions);
			this.letter = 'Y';
			this.resourceString = R.raw.upper_y;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/yc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/yc.mp4";
			break;
		case 25:
			this.info = res.getStringArray(R.array.upper_z_instructions);
			this.letter = 'Z';
			this.resourceString = R.raw.upper_z;
			this.imageString = "https://dl.dropboxusercontent.com/u/276237908/zc.jpg";
			this.videoURI = "https://dl.dropboxusercontent.com/u/276237908/zc.mp4";
			break;
		default:
			this.info = null;
			throw new Exception();
		}
	}
	
	public Bitmap getImageBitmap(String url) 
	{ 
		Bitmap bm = null; 
	    try 
	    { 
	        URL aURL = new URL(url); 
	        URLConnection conn = aURL.openConnection(); 
	        conn.connect(); 
	        InputStream is = conn.getInputStream(); 
	        BufferedInputStream bis = new BufferedInputStream(is); 
	        bm = BitmapFactory.decodeStream(bis); 
	        bis.close(); 
	        is.close(); 
	   } 
	   catch (IOException e) 
	   { 
	       Log.e(TAG, "Error getting bitmap", e); 
	   } 
	   return bm; 
	} 
}
