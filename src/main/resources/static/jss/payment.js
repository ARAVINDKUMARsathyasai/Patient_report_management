// first request server to create order

const paymentStart=()=>{
	console.log("payment strated .....");
	let amount = 250
	console.log(amount)
	if(amount=='' || amount==null){
		swal("Failed !!", "Ammount cannot be 0 !!", "error");
		return;
	}
	
	$.ajax(
		{
		url:'/user/create_order',
		data:JSON.stringify({amount:amount,info:'order_request'}),
		contentType:'application/json',
		type:'POST',
		dataType:'json',
		success:function(response){
			//invoke 	where sucess
			console.log(response)
			if(response.status=='created'){
				//open payment form
				let options={
					key:'rzp_test_dZ1Un0Fc0tNMv2',
					amount:response.amount,
					currency:'INR',
					name:'Patient Record Management',
					description:'Appointment Fee',
					image:'https://tse3.mm.bing.net/th?id=OIP.ab6k4SrCdMq8IE_kQHpnaQHaD4&pid=Api&P=0&h=180',
					order_id:response.id,
					handler:function(response){
						console.log(response.razorpay_payment_id)
						console.log(response.razorpay_order_id)
						console.log(response.razorpay_signature)
//						console.log('Payment successful !!')
                        
                        updatePaymentOnServer(response.razorpay_payment_id,
                        					  response.razorpay_order_id,"paid");
					},
					config: {
						    display: {
						      blocks: {
						        banks: {
						          name: 'Methods with Offers',
						          instruments: [
						            {
						              method: 'wallet',
						              wallets: ['olamoney']
						            }]
						        },
						      },
						      sequence: ['block.banks'],
						      preferences: {
						        show_default_blocks: true,
						      },
						    },
						  },
						  config: {
						    display: {
						      blocks: {
						        banks: {
						          name: 'Most Used Methods',
						          instruments: [
						            {
						              method: 'wallet',
						              wallets: ['freecharge'],
						            },
						            {
						              method: 'wallet',
						              wallets: ['olamoney']
						            },
						            {
						                method: 'upi'
						            },
						            ],
						        },
						      },
						      sequence: ['block.banks'],
						      preferences: {
						        show_default_blocks: true,
						      },
						    },
						  },
					prefill: {
						name: "",
						email: "",
						contact: ""
						},
						notes: {
						address: "Patient Record Management"
						
						},
						theme: {
						color: "#3399cc"
						}
				};
				
				let rzp = new Razorpay(options);
				
				rzp.on('payment.failed', function (response){
					console.log(response.error.code);
					console.log(response.error.description);
					console.log(response.error.source);
					console.log(response.error.step);
					console.log(response.error.reason);
					console.log(response.error.metadata.order_id);
					console.log(response.error.metadata.payment_id);
//					alert("Oops payment failed !!")
					swal("Failed !!", "Oops payment failed !!", "error");
					});
				
				rzp.open()
			}
		},
		error:function(error){
			//invoked when error
			console.log(error)
			alert("something went wrong!!")
		},
	});
};

function updatePaymentOnServer(payement_id,order_id,status){
	$.ajax({
		url:'/user/update_order',
		data:JSON.stringify({payment_id:payement_id,order_id:order_id,status:status}),
		contentType:'application/json',
		type:'POST',
		dataType:'json',
		success:function(response){
			swal("Good job!", "congrates !! Payment successful !!", "success");
		},
		error:function(error){
		 swal("Failed !!", "Your payment is sucessful, but we did not get on server, we will contact you as soon as possible", "error");	
		}
	});
}