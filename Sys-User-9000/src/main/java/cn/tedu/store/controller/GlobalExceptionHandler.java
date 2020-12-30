package cn.tedu.store.controller;


import cn.tedu.store.controller.ex.*;
import cn.tedu.store.ex.*;
import cn.tedu.store.ex.FileUploadException;
import cn.tedu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一处理控制器层抛出的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult<Void> handleCustomException(Throwable e){
        JsonResult<Void> jr=new JsonResult<Void>();
        jr.setMsg(e.getMessage());

        if(e instanceof RecordNotFoundException) {
            jr.setState(2001);
        }
        if(e instanceof ProductOutOfStockException) {
            jr.setState(2002);
        }
        if(e instanceof UpdateException) {
            jr.setState(2003);
        }
        if(e instanceof EmptyArgumentException) {
            jr.setState(2004);
        }
        if(e instanceof AccessDeniedException) {
            jr.setState(2005);
        }
        if(e instanceof DeleteException) {
            jr.setState(2006);
        }if(e instanceof FileEmptyException) {
            jr.setState(2007);
        }if(e instanceof FileIOException) {
            jr.setState(2008);
        }
        if(e instanceof FileSizeException) {
            jr.setState(2009);
        }
        if(e instanceof FileStateException) {
            jr.setState(2010);
        }
        if(e instanceof FileTypeException) {
            jr.setState(2011);
        }
        if(e instanceof FileUploadException) {
            jr.setState(2012);
        }
        if(e instanceof InsertException) {
            jr.setState(2013);
        }
        if(e instanceof QueryException) {
            jr.setState(2014);
        }
        if(e instanceof ServiceException) {
            jr.setState(2015);
        }
        if(e instanceof EmptyFileException) {
            jr.setState(2016);
        }
        if(e instanceof FileExternDisMatchException) {
            jr.setState(2017);
        }
        if(e instanceof FileOutOfMaxSizeException) {
            jr.setState(2018);
        }
        if(e instanceof cn.tedu.store.controller.ex.FileUploadException) {
            jr.setState(2019);
        }
        if(e instanceof ParameterErrorException) {
            jr.setState(2020);
        }
        if(e instanceof EmailDisMatch) {
            jr.setState(2021);
        }
        jr.setMsg(e.getMessage());
        return jr;
    }
}
