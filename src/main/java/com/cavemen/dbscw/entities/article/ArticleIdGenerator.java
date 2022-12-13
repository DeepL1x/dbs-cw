package com.cavemen.dbscw.entities.article;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ArticleIdGenerator implements IdentifierGenerator {
    private static final int articleLength = 8;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Supplier<Stream<String>> streamSupplier = () -> session.createQuery(query).stream();
        Stream<String> ids = streamSupplier.get();
        if (ids.toArray().length == 0){
            return "0".repeat(8);
        }
        ids = streamSupplier.get();
        Long max = ids
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, articleLength - max.toString().length())));
        return sb.toString() + (max + 1);
    }
}
