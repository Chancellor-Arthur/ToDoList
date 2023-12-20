import axios from 'axios';

export const addTodoDb = (title: string): Promise<number> => {
	return axios
		.post(
			`http://${process.env.NEXT_PUBLIC_HOST}/tasks`,
			JSON.stringify({ name: title, description: "desc", status: "TODO"}),
			{
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
				},
			}
		)
		.then((res) => res.data);
};
