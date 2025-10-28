//[SinErrores]
class A {
    public void otroMetodo(){
        for(var a = 0; a < 10; a = a + 1){
            var x = a;
        }
        var b = 0;
        for(b = 0; b < 10; b = b + 1){
            var y = b;
        }
        for(var c = true; c == false; c = false){
            var z = c;
        }
    }
}
