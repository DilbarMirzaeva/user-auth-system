package intern.backend_tasks.mapper;

public interface EntityMapper<E,D> {
    E toEntity(D d);
    D toDto(E e);
}
