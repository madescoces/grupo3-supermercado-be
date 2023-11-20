package ar.edu.unsam.serviceimport ar.edu.unsam.data.ERROR_ID_NOT_FOUNDimport ar.edu.unsam.errors.BusinessExceptionimport ar.edu.unsam.errors.NotFoundExceptionimport ar.edu.unsam.model.Repositorimport ar.edu.unsam.model.RepositorDTOimport ar.edu.unsam.repository.RepositorRepositoryimport org.springframework.beans.factory.annotation.Autowiredimport org.springframework.stereotype.Serviceimport java.util.*interface IRepositorService {    fun getAll(): List<RepositorDTO>    fun getById(idRepositor:Long):Repositor    fun create(repositor: Repositor): Repositor    fun delete(idRepositor: Long)}@Serviceclass RepositorService : IRepositorService{    @Autowired    val repositorRepository: RepositorRepository? = null    @Throws(BusinessException::class)    override fun getAll(): List<RepositorDTO> {        try {            return repositorRepository!!.findAll().map { repositor -> repositor.toDTO() }        } catch (e: Exception) {            throw BusinessException(e.message!!)        }    }    @Throws(BusinessException::class, NotFoundException::class)    override fun getById(idRepositor: Long):Repositor{        val op: Optional<Repositor>        try {            op = repositorRepository!!.findById(idRepositor)        } catch (e:Exception) {            throw NotFoundException(ERROR_ID_NOT_FOUND)        }        if(!op.isPresent) {            throw NotFoundException(ERROR_ID_NOT_FOUND)        }        return op.get()    }    @Throws(BusinessException::class)    override fun create(repositor: Repositor): Repositor {        try {            return repositorRepository!!.save(repositor)        } catch (e:Exception){            throw BusinessException(e.message!!)        }    }    @Throws(BusinessException::class, NotFoundException::class)    override fun delete(idRepositor: Long) {        val op: Optional<Repositor>        try {            op = repositorRepository!!.findById(idRepositor)        } catch (e:Exception) {            throw NotFoundException(ERROR_ID_NOT_FOUND)        }        if (!op.isPresent){            throw NotFoundException(ERROR_ID_NOT_FOUND)        }        else {            try {                repositorRepository!!.deleteById(idRepositor)            } catch (e:Exception) {                throw BusinessException(e.message!!)            }        }    }}