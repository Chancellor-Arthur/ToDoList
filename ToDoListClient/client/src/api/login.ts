import axios from 'axios';

export const login = (username: string, password: string) => {
	return axios
		.post(
			`http://${process.env.NEXT_PUBLIC_HOST}/auth/login`,
			JSON.stringify({ username:username, password:password}),
			{
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
				},
			}
		)
		.then((res) => res.data);
};
