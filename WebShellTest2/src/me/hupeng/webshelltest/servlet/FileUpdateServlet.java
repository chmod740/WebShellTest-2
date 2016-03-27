package me.hupeng.webshelltest.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/fileUpload")
public class FileUpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// ���ڴ���������Ϣ
		String message = "";

		// ���Լ�����Ŀ�й����һ�����ڴ���û���Ƭ���ļ���
		String defaultField = "uploadFiles";
		String projectpath = this.getServletContext().getRealPath(
				"/" + defaultField + "/");
		// ������ļ��в����ڣ�������ļ���
		File f = new File(projectpath);
		if (!f.exists()) {
			f.mkdir();
		}
		// ������ļ����������ڴ��JSPҳ���д��ݹ������ļ�
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ���û����С������ļ����ڻ����Сʱ�����Ȱ��ļ��ŵ�������
		factory.setSizeThreshold(4 * 1024);
		// �����ϴ��ļ��ı���·��
		factory.setRepository(f);

		// ����Servlet�ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ���ÿ����ϴ��ļ���С���Ͻ�10MB
		upload.setSizeMax(10 * 1024 * 1024);

		try {
			// ȡ�������ϴ��ļ�����Ϣ
			List<FileItem> list = upload.parseRequest(request);
			Iterator<FileItem> iter = list.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				// ������յ��Ĳ�������һ����ͨ��(��text��)��Ԫ�أ���ôִ���������
				if (!item.isFormField()) {
					String fieldName = item.getFieldName();// ��ô˱�Ԫ�ص�name����
					String fileName = item.getName();// ����ļ�������·��
					String contentType = item.getContentType();// ����ļ�����
					long fileSize = item.getSize();// ����ļ���С
					// ���ļ�������·���н�ȡ���ļ���
					// ��ȡ��չ��
					String extFile = fileName.split("\\.")[fileName.split("\\.").length - 1];
					fileName = fieldName + "." + extFile;
					File uploadedFile = new File(projectpath, fileName);

					try {
						// ����ڸ��ļ������Ѿ�����ͬ���ļ�����ô����ɾ��֮�������´�����ֻ�������ϴ�һ����Ƭ�������
						if (uploadedFile.exists()) {
							uploadedFile.delete();
						}

						item.write(uploadedFile);

					} catch (Exception e) {
						e.printStackTrace();
						// return ;
					}

				} else {
					message = "<h3>�ļ�δѡ����ļ�·�����Ϸ�������</h3>";
					// ȡ����ͨ�Ķ��󣨶������ı����������͵�ʹ�ã�
					// ������ͨ���͵Ķ����ݲ�������
					// return ;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "�ϴ�ʧ��";
			e.printStackTrace();
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("uploadResult.jsp").forward(request,
				response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.getOutputStream().write("get��������".getBytes());
	}

}
