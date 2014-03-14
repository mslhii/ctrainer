/**
 * LetterInfo.java
 * 
 * Class that represents a letter (both lower and upper case) as well its information for displaying info on the screen
 * 
 * @author Michael Hii
 */

package com.example.cursivetrainer;

import android.content.res.Resources;

public class LetterInfo
{
	char letter;
	String[] info;
	int resourceString;
	int videoString; //TODO: remove when all streaming is fixed
	String videoURI;
	//final char[] clockLetters = {'a','c','o','d','g','q'};
	//final char[] hillLetters = {'n','m','v','x','y','z'};
	//final char[] loopLetters = {'e','l','b','f','h','k'};
	//final char[] lineLetters = {'i','t','j','p','r','s', 'u', 'w'};
	final int[] clockLetters = {0, 2, 14, 3, 6, 16};
	final int[] hillLetters = {13, 12, 21, 23, 24, 25};
	final int[] loopLetters = {4, 11, 1, 5, 7, 10};
	final int[] lineLetters = {8, 19, 9, 15, 17, 18, 20, 22};
	
	private final int INVALID_LETTER = 255;
	private final int INVALID_RESOURCE = -1;
	
	public LetterInfo()
	{
		this.letter = INVALID_LETTER;
		this.info = null;
		this.resourceString = INVALID_RESOURCE;
		this.videoString = INVALID_RESOURCE;
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
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/al.mp4";
			break;
		case 1:
			this.info = res.getStringArray(R.array.b_instructions);
			this.letter = 'b';
			this.resourceString = R.raw.b;
			break;
		case 2:
			this.info = res.getStringArray(R.array.c_instructions);
			this.letter = 'c';
			this.resourceString = R.raw.c;
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/cl.mp4";
			break;
		case 3:
			this.info = res.getStringArray(R.array.d_instructions);
			this.letter = 'd';
			this.resourceString = R.raw.d;
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/dl.mp4";
			break;
		case 4:
			this.info = res.getStringArray(R.array.e_instructions);
			this.letter = 'e';
			this.resourceString = R.raw.e;
			break;
		case 5:
			this.info = res.getStringArray(R.array.f_instructions);
			this.letter = 'f';
			this.resourceString = R.raw.f;
			break;
		case 6:
			this.info = res.getStringArray(R.array.g_instructions);
			this.letter = 'g';
			this.resourceString = R.raw.g;
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/gl.mp4";
			break;
		case 7:
			this.info = res.getStringArray(R.array.h_instructions);
			this.letter = 'h';
			this.resourceString = R.raw.h;
			break;
		case 8:
			this.info = res.getStringArray(R.array.i_instructions);
			this.letter = 'i';
			this.resourceString = R.raw.i;
			break;
		case 9:
			this.info = res.getStringArray(R.array.j_instructions);
			this.letter = 'j';
			this.resourceString = R.raw.j;
			break;
		case 10:
			this.info = res.getStringArray(R.array.k_instructions);
			this.letter = 'k';
			this.resourceString = R.raw.k;
			break;
		case 11:
			this.info = res.getStringArray(R.array.l_instructions);
			this.letter = 'l';
			this.resourceString = R.raw.l;
			break;
		case 12:
			this.info = res.getStringArray(R.array.m_instructions);
			this.letter = 'm';
			this.resourceString = R.raw.m;
			break;
		case 13:
			this.info = res.getStringArray(R.array.n_instructions);
			this.letter = 'n';
			this.resourceString = R.raw.n;
			break;
		case 14:
			this.info = res.getStringArray(R.array.o_instructions);
			this.letter = 'o';
			this.resourceString = R.raw.o;
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/ol.mp4";
			break;
		case 15:
			this.info = res.getStringArray(R.array.p_instructions);
			this.letter = 'p';
			this.resourceString = R.raw.p;
			break;
		case 16:
			this.info = res.getStringArray(R.array.q_instructions);
			this.letter = 'q';
			this.resourceString = R.raw.q;
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/qul.mp4";
			break;
		case 17:
			this.info = res.getStringArray(R.array.r_instructions);
			this.letter = 'r';
			this.resourceString = R.raw.r;
			break;
		case 18:
			this.info = res.getStringArray(R.array.s_instructions);
			this.letter = 's';
			this.resourceString = R.raw.s;
			break;
		case 19:
			this.info = res.getStringArray(R.array.t_instructions);
			this.letter = 't';
			this.resourceString = R.raw.t;
			break;
		case 20:
			this.info = res.getStringArray(R.array.u_instructions);
			this.letter = 'u';
			this.resourceString = R.raw.u;
			break;
		case 21:
			this.info = res.getStringArray(R.array.v_instructions);
			this.letter = 'v';
			this.resourceString = R.raw.v;
			break;
		case 22:
			this.info = res.getStringArray(R.array.w_instructions);
			this.letter = 'w';
			this.resourceString = R.raw.w;
			break;
		case 23:
			this.info = res.getStringArray(R.array.x_instructions);
			this.letter = 'x';
			this.resourceString = R.raw.x;
			break;
		case 24:
			this.info = res.getStringArray(R.array.y_instructions);
			this.letter = 'y';
			this.resourceString = R.raw.y;
			break;
		case 25:
			this.info = res.getStringArray(R.array.z_instructions);
			this.letter = 'z';
			this.resourceString = R.raw.z;
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
			//this.videoString = R.raw.ac;
			this.videoURI = "https://dl.dropboxusercontent.com/u/22280001/ac.mp4";
			break;
		case 1:
			this.info = res.getStringArray(R.array.upper_b_instructions);
			this.letter = 'B';
			this.resourceString = R.raw.upper_b;
			break;
		case 2:
			this.info = res.getStringArray(R.array.upper_c_instructions);
			this.letter = 'C';
			this.resourceString = R.raw.upper_c;
			break;
		case 3:
			this.info = res.getStringArray(R.array.upper_d_instructions);
			this.letter = 'D';
			this.resourceString = R.raw.upper_d;
			break;
		case 4:
			this.info = res.getStringArray(R.array.upper_e_instructions);
			this.letter = 'E';
			this.resourceString = R.raw.upper_e;
			break;
		case 5:
			this.info = res.getStringArray(R.array.upper_f_instructions);
			this.letter = 'F';
			this.resourceString = R.raw.upper_f;
			break;
		case 6:
			this.info = res.getStringArray(R.array.upper_g_instructions);
			this.letter = 'G';
			this.resourceString = R.raw.upper_g;
			break;
		case 7:
			this.info = res.getStringArray(R.array.upper_h_instructions);
			this.letter = 'H';
			this.resourceString = R.raw.upper_h;
			break;
		case 8:
			this.info = res.getStringArray(R.array.upper_i_instructions);
			this.letter = 'I';
			this.resourceString = R.raw.upper_i;
			break;
		case 9:
			this.info = res.getStringArray(R.array.upper_j_instructions);
			this.letter = 'J';
			this.resourceString = R.raw.upper_j;
			break;
		case 10:
			this.info = res.getStringArray(R.array.upper_k_instructions);
			this.letter = 'K';
			this.resourceString = R.raw.upper_k;
			break;
		case 11:
			this.info = res.getStringArray(R.array.upper_l_instructions);
			this.letter = 'L';
			this.resourceString = R.raw.upper_l;
			break;
		case 12:
			this.info = res.getStringArray(R.array.upper_m_instructions);
			this.letter = 'M';
			this.resourceString = R.raw.upper_m;
			break;
		case 13:
			this.info = res.getStringArray(R.array.upper_n_instructions);
			this.letter = 'N';
			this.resourceString = R.raw.upper_n;
			break;
		case 14:
			this.info = res.getStringArray(R.array.upper_o_instructions);
			this.letter = 'O';
			this.resourceString = R.raw.upper_o;
			break;
		case 15:
			this.info = res.getStringArray(R.array.upper_p_instructions);
			this.letter = 'P';
			this.resourceString = R.raw.upper_p;
			break;
		case 16:
			this.info = res.getStringArray(R.array.upper_q_instructions);
			this.letter = 'Q';
			this.resourceString = R.raw.upper_q;
			break;
		case 17:
			this.info = res.getStringArray(R.array.upper_r_instructions);
			this.letter = 'R';
			this.resourceString = R.raw.upper_r;
			break;
		case 18:
			this.info = res.getStringArray(R.array.upper_s_instructions);
			this.letter = 'S';
			this.resourceString = R.raw.upper_s;
			break;
		case 19:
			this.info = res.getStringArray(R.array.upper_t_instructions);
			this.letter = 'T';
			this.resourceString = R.raw.upper_t;
			break;
		case 20:
			this.info = res.getStringArray(R.array.upper_u_instructions);
			this.letter = 'U';
			this.resourceString = R.raw.upper_u;
			break;
		case 21:
			this.info = res.getStringArray(R.array.upper_v_instructions);
			this.letter = 'V';
			this.resourceString = R.raw.upper_v;
			break;
		case 22:
			this.info = res.getStringArray(R.array.upper_w_instructions);
			this.letter = 'W';
			this.resourceString = R.raw.upper_w;
			break;
		case 23:
			this.info = res.getStringArray(R.array.upper_x_instructions);
			this.letter = 'X';
			this.resourceString = R.raw.upper_x;
			break;
		case 24:
			this.info = res.getStringArray(R.array.upper_y_instructions);
			this.letter = 'Y';
			this.resourceString = R.raw.upper_y;
			break;
		case 25:
			this.info = res.getStringArray(R.array.upper_z_instructions);
			this.letter = 'Z';
			this.resourceString = R.raw.upper_z;
			break;
		default:
			this.info = null;
			throw new Exception();
		}
	}
}
