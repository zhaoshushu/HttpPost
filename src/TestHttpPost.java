import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestHttpPost {

	public static void main(String[] args) {
		new ReadByPost().start();
	}
	
	static class ReadByPost extends Thread{
		@Override
		public void run() {
			
			try {
				
				URL url = new URL("http://fanyi.youdao.com/openapi.do");
				HttpURLConnection httpURLConnection =  (HttpURLConnection) url.openConnection();
				httpURLConnection.addRequestProperty("encoding", "UTF-8");
				httpURLConnection.setDoInput(true); //允许输入
				httpURLConnection.setDoOutput(true); //允许输出
				httpURLConnection.setRequestMethod("POST");
				
				
				OutputStream outputStream = httpURLConnection.getOutputStream();
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				
				bufferedWriter.write("keyfrom=zyqtesthttpget&key=1803791190&type=data&doctype=xml&version=1.1&q=Hello");
				bufferedWriter.flush(); //强制执行
				
				InputStream inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
				String line;
				StringBuilder stringBuilder = new StringBuilder();
				while ((line=bufferedReader.readLine())!=null) {
					stringBuilder.append(line);
				}
				System.out.println(stringBuilder.toString());
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
