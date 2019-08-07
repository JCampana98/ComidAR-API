package ar.edu.um.comidar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.um.comidar.entity.PaymentMethod;
import ar.edu.um.comidar.repository.PaymentMethodRepository;

@Service
@Transactional
public class PaymentMethodService extends ServiceImplement<PaymentMethod, Long> {
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	@Override
	public void create(PaymentMethod entity) {
		super.create(entity);
	}

	@Override
	public void update(PaymentMethod entity) {
		super.update(entity);
	}

	@Override
	public void remove(PaymentMethod entity) {
		super.remove(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public PaymentMethod findById(Long id) {
		return super.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PaymentMethod> findAll() {
		return super.findAll();
	}
}
