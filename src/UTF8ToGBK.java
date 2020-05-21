import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.ProgressMonitor;

//UTF-8转换成GBK
public class UTF8ToGBK {
	
	public static int size = 0;
	
	public static ProgressMonitor p;
	
	public static void main(String[] args) throws IOException{
		//定义项目的文件夹路径（需要时把这个路径修改）
		String oldFolderLocation = "E:/高清壁纸/笔记.txt";
		File oldFolderLocationFile = new File(oldFolderLocation);
				
		//定义新文件的路径（需要时把这个路径修改）
		String newFolderLocation = "E:/测试";
		//用于获取oldFolderLocation的最后一个文件路径
		int index = oldFolderLocation.lastIndexOf("/");
		String FileForNeed = oldFolderLocation.substring(index + 1,oldFolderLocation.length());
		File newFolderLocationFile = new File(newFolderLocation + "/" + FileForNeed);
		TheMainTranslator(oldFolderLocationFile,newFolderLocationFile);
	}
	
	public static void TheMainTranslator(File oldFolderLocationFile,File newFolderLocationFile) {
		try {
			diguiForGetSize(oldFolderLocationFile,newFolderLocationFile);
			//设置进度条
			p = new ProgressMonitor(null,null,"正在转换，请稍后...",0,size);
			p.setMillisToPopup(0);
			p.setMillisToDecideToPopup(0);
			size = 0;
			digui(oldFolderLocationFile, newFolderLocationFile);
			p.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//使用递归(第一个参数是要处理的文件或文件夹，第二个参数是新文件夹地址)
	public static void digui(File file,File newFolderLocationFile) throws IOException{
		//如果是文件夹
		if(file.isDirectory()){
			//如果文件夹不存在则创建新的文件夹
			if(!newFolderLocationFile.exists()){
				newFolderLocationFile.mkdir();
			}
			//取出子文件
			File[] files = file.listFiles();
			//循环这些子文件
			for(int i = 0;i < files.length;i++){
				//如果不是文件夹则创建对应的文件
				if(!files[i].isDirectory()){
					//创建文件名
					File newFile = new File(newFolderLocationFile,files[i].getName());
					//使用输入输出流来转换字符流
					FileInputStream is = new FileInputStream(files[i]);
					FileOutputStream os = new FileOutputStream(newFile);
					//以UTF-8编码写入，以GBK编码写出
					InputStreamReader isr = new InputStreamReader(is,"UTF-8");
					OutputStreamWriter osw = new OutputStreamWriter(os,"GBK");
						
					//读入单个字符
					int c = isr.read();
					//写入单个字符
					while(c != -1){
						p.setProgress(size++);
						osw.write(c);
						c = isr.read();
					}
						
					//输出文件的名字
					System.out.println(newFile.getName());
					isr.close();
					osw.close();
					is.close();
					os.close();
				}else{
					//如果是文件夹则递归寻找子文件
					File newFolderLocationFile2 = new File(newFolderLocationFile,files[i].getName());
					digui(files[i],newFolderLocationFile2);
				}
			}
		}else {
			//如果不是文件夹则直接进行文件写入
			//若文件不存在则创建
			File newFile = new File(newFolderLocationFile.toString());
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			System.out.println(newFile);
			//使用输入输出流来转换字符流
			FileInputStream is = new FileInputStream(file);
			FileOutputStream os = new FileOutputStream(newFile);
			//以GBK编码写入，以UTF-8编码写出
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			OutputStreamWriter osw = new OutputStreamWriter(os,"GBK");
			
			//读入单个字符
			int c = isr.read();
			//写入单个字符
			while(c != -1){
				p.setProgress(size++);
				osw.write(c);
				c = isr.read();
			}
			isr.close();
			osw.close();
			is.close();
			os.close();
		}
	}
		
	//使用递归获取进度条总大小（大体和以上方法一样）
	public static void diguiForGetSize(File file,File newFolderLocationFile) throws IOException{
		if(file.isDirectory()){
			if(!newFolderLocationFile.exists()){
				newFolderLocationFile.mkdir();
			}
			File[] files = file.listFiles();
			for(int i = 0;i < files.length;i++){
				if(!files[i].isDirectory()){
					//此次读取是为了确定进度条的最大值
					FileInputStream is2 = new FileInputStream(files[i]);
					InputStreamReader isr2 = new InputStreamReader(is2,"UTF-8");
					int cc = isr2.read();
					while(cc != -1) {
						size++;
						cc = isr2.read();
					}
					isr2.close();
				}else{
					//如果是文件夹则递归寻找子文件
					File newFolderLocationFile2 = new File(newFolderLocationFile,files[i].getName());
					diguiForGetSize(files[i],newFolderLocationFile2);
				}
			}
		}else {
			//如果不是文件夹
			//使用输入输出流来转换字符流
			FileInputStream is = new FileInputStream(file);
			//以GBK编码写入，以UTF-8编码写出
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
				
			//读入单个字符
			int c = isr.read();
			//写入单个字符
			while(c != -1){
				size++;
				c = isr.read();
			}
			isr.close();
			is.close();
		}
	}
	
}
