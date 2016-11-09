package com.jhcz.base.service.impl;

import com.jhcz.base.domain.Sequence;
import com.jhcz.base.exception.ServiceException;
import com.jhcz.base.dao.SequenceDao;
import com.jhcz.base.service.SequenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SequenceServiceImpl implements SequenceService
{
	
	@Resource
	SequenceDao sequenceDao;
	
	public String getNextSequence(String name)
	{
		Sequence sequence = sequenceDao.getNextSequence(name.toUpperCase());
		
		if (sequence == null)
		{
			createSequence(name);
			return "1";
		}else{
			
			long curVal = Long.parseLong(sequence.getCurrent_value());
			long step = Long.parseLong(sequence.getStep());
			long rollBack = Long.parseLong(sequence.getRoll_back());
			long maxValue = Long.parseLong(sequence.getMax_value());
			long nextSeq = 0L;
			
			String seqRet = Long.toString(curVal);
			
			if (Math.abs(curVal) > Math.abs(maxValue))
			{
				if (rollBack == 1)
				{
					
					seqRet = sequence.getStart_value();
					nextSeq = Long.parseLong(seqRet) + step;
				}
				else
				{
					throw new ServiceException("已经超过SEQUENCE的最大值");
				}
			}
			else
			{
				nextSeq = curVal + step;
			}
			
			sequence.setCurrent_value(Long.toString(nextSeq));
			sequenceDao.updateSequence(sequence);
			
			return seqRet;
		}
	}
	
	public void createSequence(String name)
	{
		sequenceDao.createSequence(name.toUpperCase());
	}
}
