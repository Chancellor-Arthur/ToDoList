import axios from 'axios';

export const deleteTodoDb = (id: number): void => {
	axios.delete(`http://${process.env.NEXT_PUBLIC_HOST}/api/task/${id}`);
};
