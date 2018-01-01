package org.scalatra.example

import org.scalatra._
import servlet.{FileUploadSupport, MultipartConfig, SizeConstraintExceededException}
import xml.Node

import java.io.File

class FileUploadExample extends ScalatraServlet with FileUploadSupport {

  configureMultipartHandling(MultipartConfig(maxFileSize = Some(3*1024*1024)))

  def displayPage(content: Seq[Node]) = Template.page("File upload example", content, url(_))

  error {
    case e: SizeConstraintExceededException => {
      RequestEntityTooLarge(displayPage(
        <p>The file you uploaded exceeded the 3 MB limit.</p>))
    }
  }

  get("/") {
    displayPage(
      <form action={url("/")} method="post" enctype="multipart/form-data">
       <p>File to upload: <input type="file" name="file" /></p>
       <p><input type="submit" name="upload" value="Upload" /></p>
      </form>
      <p>
        Upload a file using the above form. After you hit "Upload"
        the file will be uploaded and your browser will start
        downloading it.
      </p>

      <p>
        The maximum file size accepted is 3 MB.
      </p>)
  }

  post("/") {
    fileParams.get("file") match {
      case Some(file) if (file.name != "") =>
        Ok(file.get(), Map(
          "Content-Type"        -> (file.contentType.getOrElse("application/octet-stream")),
          "Content-Disposition" -> ("attachment; filename=\"" + file.name + "\"")
        ))

      case _ =>
        BadRequest(displayPage(
          <p>
            Hey! You forgot to select a file.
          </p>))
    }
  }
}
