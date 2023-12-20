import { Task } from '@/types/types';
import axios from 'axios';

export const updateTodoDb = ({ id, title, done }: Task): void => {
	axios.put(
		`http://${process.env.NEXT_PUBLIC_HOST}/api/task/${id}`,
		JSON.stringify({ id: id, title: title, done: done }),
		{
			headers: {
				'Content-Type': 'application/json; charset=utf-8',
			},
		}
	);
};
