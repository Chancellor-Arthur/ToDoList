'use client';
import {
	Stack,
	TextField,
	Theme,
	styled, IconButton,
} from '@mui/material';
import {useState} from "react";
import {useUser} from "@store";
import {login} from "@api/login";
import {useRouter} from "next/navigation";

const Login = () => {
	const [inputUsername, setInputUsername] = useState<string>();
	const [inputPassword, setInputPassword] = useState<string>();

	const loginZustand = useUser((user) => user.loginUser);
	const router = useRouter();
	// @TODO: переписать красиво. Назвать функцию по факту
	const sendDataToBackend = async () => {
		console.log('sending to back (login)');
		if (!inputUsername || !inputPassword) return;

		let authUser ={};
		try {
			authUser = await login(inputUsername, inputPassword);
			loginZustand(inputUsername);
			console.log('login user:', authUser);
			router.push("/")
		} catch (e) {
			console.error('loginPage error: ', e) // @TODO: добавить логгер
		}
	};

	return (
		<StyledStack>
			<TextField
				type="text"
				label="Имя пользователя"
				variant="outlined"
				fullWidth
				autoFocus
				sx={{ p: '8px 8px 8px 0px' }}
				InputProps={InputProps}
				inputProps={{ maxLength: 32 }}
				value={inputUsername}
				onChange={(e) => setInputUsername(e.target.value)}
			/>
			<TextField
				type="text"
				label="Пароль"
				variant="outlined"
				fullWidth
				sx={{ p: '8px 8px 8px 0px' }}
				InputProps={InputProps}
				inputProps={{ maxLength: 100 }}
				value={inputPassword}
				onChange={(e) => setInputPassword(e.target.value)}
			/>
			<IconButton sx={{ m: 1 }} onClick={() => sendDataToBackend()}>
				Войти
			</IconButton>
		</StyledStack>
	);
};

export default Login;

const InputProps = {
	disableUnderline: true,
};

const StyledStack = styled(Stack)(({ theme }: { theme: Theme }) => ({
	margin: '24px 80px',
	padding: '8px',
	border: `1px solid ${theme.palette.border.main}`,
	borderRadius: '10px',
	backgroundColor: `${theme.palette.backgroundColors.main}`,
	'@media (max-width: 890px)': {
		margin: '24px 10px',
	},
}));
