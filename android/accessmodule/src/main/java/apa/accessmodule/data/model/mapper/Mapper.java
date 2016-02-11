package apa.accessmodule.data.model.mapper;

/**
 * Created by alberto on 7/2/16.
 */
public interface Mapper<Input,Output> {
    Output map(Input input);
}
