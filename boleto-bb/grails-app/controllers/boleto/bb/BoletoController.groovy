package boleto.bb

import javax.servlet.ServletOutputStream

class BoletoController {


    GeradorBoletoService geradorBoletoService
    def index() {



        def bytes = geradorBoletoService.gerar()

        /* HERE IS MY ANSWER, properly */
        response.setContentType('application/pdf')
        response.setContentLength(bytes.length)
        response.setHeader('Content-Disposition', 'inline; filename="boleto.pdf"')
        ServletOutputStream outputStream = response.getOutputStream()
        outputStream.write(bytes,0,bytes.length)
        outputStream.flush()
        outputStream.close()

    }
}
