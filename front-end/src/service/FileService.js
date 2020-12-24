class FileService {

    types = new Map();

    setTypes() {
        this.types.set('pdf', 'application/pdf')
        this.types.set('txt', 'text/plain')
    }

    saveByteArray(fileName, byte) {
        let ext = this.getExtension(fileName)
        let mimeType = this.getType(ext)
        let blob = new Blob([byte], {type: mimeType});
        let link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = fileName;
        link.click();
    }

    getExtension(fileName) {
        let regexFileExtension = /(\w+)$/; // match file extension
        return fileName.match(regexFileExtension);
    }

    getType(ext) {
        this.setTypes()
        if (this.types.has(ext))
            return this.types.get(ext);
        else
            return 'application/octet-stream'
    }
}

export default new FileService();
