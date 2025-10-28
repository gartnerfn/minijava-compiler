//[SinErrores]

class TestReturnUbicaciones {
    int metodo1(int x) {
        if (x > 0) {
            return x;
        } else {
            return -x;
        }
    }
    
    int metodo2(int x) {
        while (x > 0) {
            if (x == 5) {
                return x;
            }
            x = x - 1;
        }
        return 0;
    }
}