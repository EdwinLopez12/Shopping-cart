package api.carrito.compras.infrastructure.persistence.mapper;

import api.carrito.compras.domain.exception.PageableDataResponseModel;
import api.carrito.compras.domain.exception.PageableGeneralResponseModel;
import api.carrito.compras.domain.model.ExceptionResponseModel;
import api.carrito.compras.domain.model.GeneralDataResponseModel;
import api.carrito.compras.domain.model.GeneralResponseModel;
import api.carrito.compras.domain.model.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneralResponseModelMapper {

    static final String SELF = "self";
    static final String NEXT = "next";
    static final String PREV = "prev";
    static final String FIRST = "first";
    static final String LAST = "last";

    /**
     * asignar datos a una respuesta general
     *
     * @param type    the type
     * @param message the message
     * @param data    the data
     * @param status  the status
     * @return the general response model
     */
    public GeneralResponseModel responseToGeneralResponseModel(Integer code, String type, String message, List<Object> data, String status){
        GeneralDataResponseModel generalDataResponseModel = new GeneralDataResponseModel();
        return getGeneralResponseModel(code, type, message, data, status, generalDataResponseModel);
    }

    private GeneralResponseModel getGeneralResponseModel(Integer code, String type, String message, List<Object> data, String status, GeneralDataResponseModel generalDataResponseModel){
        if(code != null) generalDataResponseModel.setCode(code);
        if(type != null) generalDataResponseModel.setType(type);
        if(message != null) generalDataResponseModel.setMessage(message);
        generalDataResponseModel.setData(data);

        GeneralResponseModel generalResponseModel = new GeneralResponseModel();
        if(status != null) generalResponseModel.setStatus(status);
        generalResponseModel.setResponse(generalDataResponseModel);

        return generalResponseModel;
    }

    /**
     * asignar excepciones a una respuesta general
     *
     * @param status  the status
     * @param code    the code
     * @param message the message
     * @param path    the path
     * @return the general response model
     */
    public GeneralResponseModel responseToGeneralExceptionResponseModel(String status, Integer code, String message, String path){

        ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel();
        return getGeneralResponseModelWithException(status, code, message, path, exceptionResponseModel);
    }

    /**
     * Excepciones de argumentos no validos a una respuesta general
     *
     * @param status                 the status
     * @param code                   the code
     * @param message                the message
     * @param path                   the path
     * @param exceptionResponseModel the exception model response
     * @return the general response model
     */
    public GeneralResponseModel responseToInvalidMethodArgumentExceptionResponseModel(String status, Integer code, String message, String path, ExceptionResponseModel exceptionResponseModel){

        return getGeneralResponseModelWithException(status, code, message, path, exceptionResponseModel);
    }

    private GeneralResponseModel getGeneralResponseModelWithException(String status, Integer code, String message, String path, ExceptionResponseModel exceptionResponseModel) {
        if(code != null ) exceptionResponseModel.setCode(code);
        if(message != null) exceptionResponseModel.setMessage(message);
        if(path != null) exceptionResponseModel.setPath(path);

        GeneralResponseModel generalResponseModel = new GeneralResponseModel();
        if(status != null) generalResponseModel.setStatus(status);
        generalResponseModel.setException(exceptionResponseModel);

        return generalResponseModel;
    }

    /**
     * Asignar respuesta paginada a un modelo de datos de respuesta paginada general
     *
     * @param type          the type
     * @param message       the message
     * @param content       the content
     * @param size          the size
     * @param totalPages    the total pages
     * @param totalElements the total elements
     * @param currentPage   the current page
     * @return the pageable data response model
     */
    public PageableDataResponseModel pageableResponseToPageableDataResponseModel(String type, String message, Object content, Integer size, Integer totalPages, Long totalElements, Integer currentPage){
        PageableDataResponseModel pageableDataResponseModel = new PageableDataResponseModel();
        if(type != null) pageableDataResponseModel.setType(type);
        if(message != null) pageableDataResponseModel.setMessage(message);
        pageableDataResponseModel.setData(content);
        pageableDataResponseModel.setPageSize(size);
        pageableDataResponseModel.setTotalPages(totalPages);
        pageableDataResponseModel.setTotalElements(totalElements);
        pageableDataResponseModel.setCurrentPage(currentPage);
        return pageableDataResponseModel;
    }

    /**
     * Asignar modelo de datos paginados al modelo de respuesta paginada general
     *
     * @param hasPrevious               the has previous
     * @param previousPage              the previous page
     * @param path                      the path
     * @param pageableDataResponseModel the pageable data response model
     * @return the pageable general response model
     */
    public PageableGeneralResponseModel pageableResponseToPageableGeneralResponseModel(Boolean hasPrevious, Integer previousPage, String path, PageableDataResponseModel pageableDataResponseModel){

        pageableDataResponseModel.setLinks(getLinks(pageableDataResponseModel.getPageSize(), pageableDataResponseModel.getCurrentPage(), pageableDataResponseModel.getTotalPages(), hasPrevious, previousPage, path));

        PageableGeneralResponseModel pageableGeneralResponseModel = new PageableGeneralResponseModel();
        pageableGeneralResponseModel.setResponse(pageableDataResponseModel);

        return pageableGeneralResponseModel;
    }

    private List<Link> getLinks(Integer size, Integer currentPage, Integer totalPages, Boolean hasPrevious, Integer previousPage, String path){
        List<Link> links = new ArrayList<>();
        Link self = link(SELF, currentPage, size, path);
        Link first = link(FIRST, 0, size, path); // La primera siempre será 0
        Link last;

        if(totalPages == 0){
            last = link(LAST, totalPages, size, path);
        }else{
            last = link(LAST, totalPages - 1, size, path);
        }
        links.add(last);

        if(Boolean.TRUE.equals(hasPrevious) && previousPage <= totalPages-1){
            Link prev = link(PREV, previousPage, size, path);
            links.add(prev);
        }
        if(currentPage+1 <= totalPages-1){
            Link next = link(NEXT, currentPage+1 , size, path);
            links.add(next);
        }
        links.add(self);
        links.add(first);
        return links;
    }

    private Link link(String rel, Integer page, Integer pageSize, String path ){
        return Link.builder()
                .rel(rel)
                .href(path+"/?page="+page+"&size="+pageSize)
                .build();
    }
}
