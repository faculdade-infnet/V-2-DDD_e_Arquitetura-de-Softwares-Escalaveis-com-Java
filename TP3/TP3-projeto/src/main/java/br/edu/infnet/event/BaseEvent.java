package br.edu.infnet.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent<T> implements Serializable {

    private T id;
}
