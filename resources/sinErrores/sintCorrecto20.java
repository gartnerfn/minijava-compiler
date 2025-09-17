///[SinErrores]
class Chains {
    public Chains() { this.foo().bar(1).baz; }
    static Chains foo() { return new Chains(); }
    Chains bar(int x) { return this; }
}