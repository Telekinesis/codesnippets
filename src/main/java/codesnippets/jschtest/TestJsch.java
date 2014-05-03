package codesnippets.jschtest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.telekinesis.commonclasses.debug.ListPrinter;
import org.telekinesis.commonclasses.io.InputStreamLineReader;
import org.telekinesis.commonclasses.io.LineParser;
import org.telekinesis.commonclasses.io.ssh.JschConnector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class TestJsch {
	private static final String host = "192.168.167.130";
	private static final String username = "minoru";
	private static final String password = "65365737";

	private static final String remotePath = "/home/minoru";
	private static final String remoteFile = remotePath + "/aaa.txt";

	@Test
	public void testls() throws JSchException, SftpException {
		Session session = JschConnector.connect(host, username, password);
		try {
			List<String> files = JschConnector.ls(session, remotePath);
			ListPrinter.print(files);
		} finally {
			session.disconnect();
		}
	}
	
	@Test
	public void testAccessFile() throws JSchException, SftpException, IOException{
		Session session = JschConnector.connect(host, username, password);
		ChannelSftp channel = JschConnector.getSftpChannel(session);
		try{
			InputStream stream = channel.get(remoteFile);
			InputStreamLineReader reader = new InputStreamLineReader();
			LineParser parser = new LineParser() {
				
				@Override
				public void parse(String line) {
					System.out.println(line);
				}
				
				@Override
				public void onEnd() {
				}
			};
			reader.read(stream, parser, 0);
		}finally{
			channel.disconnect();
			session.disconnect();
		}
	}
}
