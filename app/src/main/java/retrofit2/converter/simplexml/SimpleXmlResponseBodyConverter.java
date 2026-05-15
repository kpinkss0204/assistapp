package retrofit2.converter.simplexml;

import java.io.IOException;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes2.dex */
final class SimpleXmlResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Class<T> cls;
    private final Serializer serializer;
    private final boolean strict;

    SimpleXmlResponseBodyConverter(Class<T> cls, Serializer serializer, boolean z) {
        this.cls = cls;
        this.serializer = serializer;
        this.strict = z;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // retrofit2.Converter
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            try {
                T t = (T) this.serializer.read((Class) this.cls, responseBody.charStream(), this.strict);
                if (t != null) {
                    return t;
                }
                throw new IllegalStateException("Could not deserialize body as " + this.cls);
            } catch (IOException e) {
                throw e;
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        } finally {
            responseBody.close();
        }
    }
}
