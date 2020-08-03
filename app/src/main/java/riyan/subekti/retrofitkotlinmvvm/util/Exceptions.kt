package riyan.subekti.retrofitkotlinmvvm.util

import java.io.IOException

class ApiExecptions(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)