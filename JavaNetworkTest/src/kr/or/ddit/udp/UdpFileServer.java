package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileServer {
	public static final int BUFFER_SIZE = 10000;
	
	public static void main(String[] args) {
		String serverIp = "127.0.0.1";
		int port = 8888;
		
		File file = new File("d:/D_Other/2002.jpg");
		
		DatagramSocket ds = null;
		
		if(!file.exists()) {
			System.out.println("파일이 존재하지않습니다.");
			System.exit(0);
		}
		long filesize = file.length();
		long totalReadBytes = 0;
		
		double startTime = 0;
		try {
			ds = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(serverIp);
			startTime = System.currentTimeMillis();
			String str = "start";
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			ds.send(dp);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[BUFFER_SIZE];
			
			str = String.valueOf(filesize);
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			ds.send(dp);
			
			while(true) {
				Thread.sleep(10); //패킷전송간의 간격을 주기 위해서
				int readBytes = fis.read(buffer,0,buffer.length);
				if(readBytes == -1) {
					break;
				}
				//읽어온 파일내용 패킷에 담기
				dp = new DatagramPacket(buffer, readBytes,serverAddr, port);
				ds.send(dp);
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes + "/" + filesize + "Bytes(" + 
												(totalReadBytes * 100 / filesize) + "%)");
			}
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (filesize / 1000) / diffTime;
			
			System.out.println("time : " + diffTime + "seconds");
			System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s" );
			
			str = "end";
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length,serverAddr,port);
			
			ds.send(dp);
			System.out.println("전송 완료...");
			fis.close();
			ds.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
