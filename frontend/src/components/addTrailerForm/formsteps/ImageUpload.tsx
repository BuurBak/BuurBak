import { useState } from 'react'
import { IoIosImages } from 'react-icons/io'
import './ImageUpload.css'
import { useDropzone } from 'react-dropzone'

export default function ImageUpload({
  trailerType,
  trailerImage,
  setTrailerImage,
}) {
  const [files, setFiles] = useState([])
  const { getRootProps, getInputProps } = useDropzone({
    accept: {
      'image/jpg': [],
      'image/png': [],
    },
    onDrop: (acceptedFiles) => {
      const newImage = acceptedFiles.map((file) =>
        Object.assign(file, {
          preview: URL.createObjectURL(file),
        })
      )
      if (files.length > 0) {
        setFiles((oldFiles) => [...oldFiles, newImage[0]])
      } else {
        setFiles(newImage)
      }
      console.log(newImage)
    },
  })

  const images = files.map((file) => (
    <img
      key={file.name}
      src={file.preview}
      alt="preview"
      className="previewImages"
    />
  ))

  return (
    <div className="formStepContainer">
      <h2>Foto's van {trailerType.trailerType} uploaden</h2>
      <p>
        Upload ten minste vier foto's van elk aanzicht van je aanhanger.
        Afbeeldingen kunnen later toegevoegd en gewijzigd worden.
      </p>
      <div className="imageUploadGrid">
        <div className="imageUploadContainer" {...getRootProps()}>
          {images.length > 0 ? (
            <div>{images[0]}</div>
          ) : (
            <>
              <IoIosImages size="56px" />
              <p>Sleep je foto's hierheen</p>
              <p>Upload ten minste 4 foto's</p>
              <button className="uploadBtn">Uploaden vanaf apparaat</button>
              <input {...getInputProps()} />
            </>
          )}
        </div>
        <div className="imageUploadFlexBox">
          <div className="imageUploadContainerSmall" {...getRootProps()}>
            {images.length > 1 ? (
              <div>{images[1]}</div>
            ) : (
              <>
                <IoIosImages size="36px" />
                <input {...getInputProps()} />
              </>
            )}
          </div>
          <div className="imageUploadContainerSmall" {...getRootProps()}>
            {images.length > 2 ? (
              <div>{images[2]}</div>
            ) : (
              <>
                <IoIosImages size="36px" />
                <input {...getInputProps()} />
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  )
}
